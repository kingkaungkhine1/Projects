/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.dao.GameDao;
import com.mycompany.dao.GameDaoImpl;
import com.mycompany.dao.RoundDao;
import com.mycompany.model.Game;
import com.mycompany.model.Round;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kaung
 */
@Service
public class ServiceLayer
{

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public List<Game> getAllGames()
    {
        List<Game> games = gameDao.getAllGames();
        for (Game game : games)
        {
            if (!game.isFinished())
            {
                game.setAnswer("****");
            }
        }

        return games;
    }

    public Game getGameById(int gameId)
    {
        Game game = gameDao.getGameById(gameId);
        if (!game.isFinished())
        {
            game.setAnswer("****");
        }

        return game;
    }

    public Game addGame(Game game)
    {
        return gameDao.addGame(game);
    }

    public int newGame()
    {
        Game game = new Game();
        game.setAnswer(createAnswer());
        game = gameDao.addGame(game);

        return game.getGameId();
    }

    private String createAnswer()
    {
        Random rnd = new Random();
        int digit1 = rnd.nextInt(10);

        int digit2 = rnd.nextInt(10);
        while (digit2 == digit1)
        {
            digit2 = rnd.nextInt(10);
        }

        int digit3 = rnd.nextInt(10);
        while (digit3 == digit2 || digit3 == digit1)
        {
            digit3 = rnd.nextInt(10);
        }

        int digit4 = rnd.nextInt(10);
        while (digit4 == digit3 || digit4 == digit2 || digit4 == digit1)
        {
            digit4 = rnd.nextInt(10);
        }

        String answer = String.valueOf(digit1) + String.valueOf(digit2)
                + String.valueOf(digit3) + String.valueOf(digit4);

        return answer;
    }

    public List<Round> getAllRoundsByGameId(int gameId)
    {
        return roundDao.getAllRoundsByGameId(gameId);
    }

    public Round makeGuess(Round round)
    {
        String answer = gameDao.getGameById(round.getGameId()).getAnswer();
        String guess = round.getGuess();
        String result = determineResult(guess, answer);
        round.setResult(result);

        if (guess.equals(answer))
        {
            Game game = getGameById(round.getGameId());
            game.setFinished(true);
            gameDao.updateGame(game);
        }

        return roundDao.addRound(round);
    }

    public String determineResult(String guess, String answer)
    {
        char[] guessChars = guess.toCharArray();
        char[] answerChars = answer.toCharArray();
        int exact = 0;
        int partial = 0;

        for (int i = 0; i < guessChars.length; i++)
        {
            // -1 indicates that index value of guessChars DNE in answer
            // otherwise the number must be in the string. Then check where
            if (answer.indexOf(guessChars[i]) > -1)
            {
                if (guessChars[i] == answerChars[i])
                {
                    exact++;
                } else
                {
                    partial++;
                }
            }
        }

        String result = "e:" + exact + ":p:" + partial;

        return result;
    }

    public List<Round> getAllRounds()
    {
        List<Round> rounds = roundDao.getAllRounds();
        return rounds;
    }

}
