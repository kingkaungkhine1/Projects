/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.main.TestApplicationConfiguration;
import com.mycompany.model.Game;
import com.mycompany.model.Round;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author kaung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDaoImplTest
{

    @Autowired
    RoundDao roundDao;

    @Autowired
    GameDao gameDao;

    public RoundDaoImplTest()
    {

    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
        List<Round> rounds = roundDao.getAllRounds();
        for(Round round: rounds)
        {
            roundDao.deleteRoundById(round.getRoundId());
        }
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testGetAllRoundsByGameId()
    {
    }

    @Test
    public void testGetRoundById()
    {
    }

    @Test
    public void testAddRound()
    {
    }


    //i think this one test encapsulates everything 
    @Test
    public void testAddGetGetAll() {
        int gameId = 1;
        
        Game game = new Game();
        game.setAnswer("5678");
        game.setFinished(false);
        game = gameDao.addGame(game);
        
        Round round = new Round();
        round.setGuess("1234");
        round.setResult("e:0:p:0");
        round.setGameId(gameId);
        roundDao.addRound(round);

        Round round2 = new Round();
        round2.setGuess("5678");
        round2.setResult("e:4:p:0");
        round2.setGameId(gameId);
        roundDao.addRound(round2);

        List<Round> rounds = roundDao.getAllRoundsByGameId(gameId);

        assertEquals(2, rounds.size());
        assertNotNull(round = roundDao.getRoundById(round.getRoundId()));
    }

}
