/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.model.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RoundDaoImpl implements RoundDao
{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Round> getAllRoundsByGameId(int gameId)
    {
        try
        {
            final String SELECT_ROUNDS_BY_GAMEID = "SELECT * FROM round "
                    + "WHERE game_id = ? ORDER BY guess_time";
            List<Round> rounds = jdbc.query(SELECT_ROUNDS_BY_GAMEID, new RoundMapper(), gameId);
            return rounds;
        } catch (DataAccessException ex)
        {
            return null;
        }
    }

    @Override
    public Round getRoundById(int roundId)
    {
        try
        {
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE round_id = ?";
            return jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(), roundId);
        } catch (DataAccessException ex)
        {
            return null;
        }
    }

    @Override
    @Transactional
    public Round addRound(Round round)
    {
        final String INSERT_ROUND = "INSERT INTO round(game_id, guess, result) VALUES(?,?,?)";
        jdbc.update(INSERT_ROUND, round.getGameId(), round.getGuess(), round.getResult());

        int newRoundId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(newRoundId);
        return getRoundById(newRoundId);
    }

    @Override
    public List<Round> getAllRounds()
    {
        final String SELECT_ALL_ROUNDS = "SELECT * FROM round";
        return jdbc.query(SELECT_ALL_ROUNDS, new RoundMapper());
    }

    //use for testing lol
    @Override
    public void deleteRoundById(int roundId)
    {
        final String DELETE_ROUND_BY_ID = "DELETE FROM round WHERE round_id = ?";
        jdbc.update(DELETE_ROUND_BY_ID, roundId);
    }
    
    @Override
    public void deleteRoundByGameId(int gameId)
    {
        final String DELETE_ROUND_BY_G_ID = "DELETE FROM round WHERE game_id = ?";
        jdbc.update(DELETE_ROUND_BY_G_ID, gameId);
    }

    /*
    @Override
    @Transactional
    public void deleteEmployeeById(int id) {
        final String DELETE_MEETING_EMPLOYEE = "DELETE FROM meeting_employee "
                + "WHERE employeeId = ?";
        jdbc.update(DELETE_MEETING_EMPLOYEE, id);
        
        final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";
        jdbc.update(DELETE_EMPLOYEE, id);
    }
     */
    public static final class RoundMapper implements RowMapper<Round>
    {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException
        {
            Round round = new Round();
            round.setRoundId(rs.getInt("round_id"));
            round.setGameId(rs.getInt("game_id"));
            round.setGuess(rs.getString("guess"));

            Timestamp timestamp = rs.getTimestamp("guess_time");
            round.setGuessTime(timestamp.toLocalDateTime());

            round.setResult(rs.getString("result"));
            return round;
        }
    }

}
