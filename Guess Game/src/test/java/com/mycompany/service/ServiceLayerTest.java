/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.main.TestApplicationConfiguration;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kaung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ServiceLayerTest
{
    @Autowired
    ServiceLayer service;

    
    public ServiceLayerTest()
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
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void testGetAllGames()
    {
    }

    @Test
    public void testGetGameById()
    {
    }

    @Test
    public void testAddGame()
    {
    }

    @Test
    public void testNewGame()
    {
    }

    @Test
    public void testGetAllRoundsByGameId()
    {
    }

    @Test
    public void testMakeGuess()
    {
    }

    @Test
    public void testDetermineResult()
    {
    }

    @Test
    public void testGetAllRounds()
    {
    }
    
    @Test
    public void testDetermineResult1() {
        String guess = "1234";
        String answer = "2159";
        String result = service.determineResult(guess, answer);

        assertEquals("e:0:p:2", result);
    }

    @Test
    public void testDetermineResult2() {
        String guess = "1234";
        String answer = "1234";
        String result = service.determineResult(guess, answer);

        assertEquals("e:4:p:0", result);
    }

    @Test
    public void testDetermineResult3() {
        String guess = "1234";
        String answer = "4321";
        String result = service.determineResult(guess, answer);

        assertEquals("e:0:p:4", result);
    }

    @Test
    public void testDetermineResult4() {
        String guess = "1234";
        String answer = "1324";
        String result = service.determineResult(guess, answer);

        assertEquals("e:2:p:2", result);
    }

    @Test
    public void testDetermineResult5() {
        String guess = "1234";
        String answer = "5678";
        String result = service.determineResult(guess, answer);

        assertEquals("e:0:p:0", result);
    }
}
