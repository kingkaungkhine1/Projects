/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.dao.GameDao;
import com.mycompany.model.Game;
import com.mycompany.model.Round;
import com.mycompany.service.ServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kaung
 */
@RestController
@RequestMapping("/api")
public class gameController
{

    @Autowired
    ServiceLayer service;

    @GetMapping("/game")
    public List<Game> all()
    {
        return service.getAllGames();
    }

    @GetMapping("/game/{game_id}")
    public Game getGameById(@PathVariable("game_id") int gameId)
    {
        return service.getGameById(gameId);
    }

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int createGame()
    {
        return service.newGame();
    }

    @GetMapping("/rounds/{game_id}")
    public List<Round> getRoundsForGame(@PathVariable("game_id") int gameId)
    {
        return service.getAllRoundsByGameId(gameId);
    }
    
    @GetMapping("/rounds")
    public List<Round> getAllRounds()
    {
        return service.getAllRounds();
    }
    
    
    @PostMapping("/guess")
    public Round makeGuess(@RequestBody Round round) {
        return service.makeGuess(round);   
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public ToDo create(@RequestBody ToDo todo)
//    {
//        return dao.add(todo);
//    }
}
