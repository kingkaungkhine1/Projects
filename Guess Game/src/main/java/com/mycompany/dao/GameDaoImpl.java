/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.model.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kaung
 */
@Repository
public class GameDaoImpl implements GameDao
{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Game> getAllGames()
    {
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
    }

    @Override
    public Game getGameById(int gameId) {
        try {
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE game_id = ?";
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), gameId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Game addGame(Game game) {
        final String INSERT_GAME = "INSERT INTO game(answer) VALUES (?)";
        jdbc.update(INSERT_GAME, game.getAnswer());
        
        int newGameId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newGameId);
        return game;
    }

    @Override
    public void updateGame(Game game) {
        final String UPDATE_GAME = "UPDATE game SET finished = ? WHERE game_id = ?";
        jdbc.update(UPDATE_GAME, game.isFinished(), game.getGameId());
    }

    //use for testing lol
    @Override
    public void deleteGameById(int gameId)
    {
        final String DELETE_GAME_BY_ID = "DELETE FROM game WHERE game_id = ?";
        jdbc.update(DELETE_GAME_BY_ID, gameId);
    }
    
    /*
        //use for testing lol
    @Override
    public void deleteRoundById(int roundId)
    {
        final String DELETE_ROUND_BY_ID = "DELETE FROM round WHERE round_id = ?";
        jdbc.update(DELETE_ROUND_BY_ID, roundId);
    }
    
    */

    public static final class GameMapper implements RowMapper<Game>
    {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException
        {
            Game game = new Game();
            game.setGameId(rs.getInt("game_id"));
            game.setAnswer(rs.getString("answer"));
            game.setFinished(rs.getBoolean("finished"));
            return game;
        }
    }

}
