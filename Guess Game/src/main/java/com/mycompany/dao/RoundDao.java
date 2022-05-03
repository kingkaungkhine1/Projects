/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.model.Round;
import java.util.List;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author kaung
 */
public interface RoundDao
{

    List<Round> getAllRoundsByGameId(int gameId);

    Round getRoundById(int roundId);

    Round addRound(Round round);

    public List<Round> getAllRounds();

    void deleteRoundById(int roundId);

    public void deleteRoundByGameId(int gameId);

}
