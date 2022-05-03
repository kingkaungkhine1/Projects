/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealershipcapstone.services;

import com.sg.cardealershipcapstone.data.MakeRepository;
import com.sg.cardealershipcapstone.models.Make;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kaung
 */
@Service
public class MakeService {

    @Autowired
    MakeRepository repo;
    
    public Result<List<Make>> getAllMakes() {
        Result<List<Make>> result = new Result<>();
        result.setPayload(repo.findAll());
        return result;
    }

    public Result<Make> saveMake(Make m) {
        Result<Make> result = validate(m);
        if (result.isSuccess()) {
            m = repo.save(m);
            result.setPayload(m);
        }
        return result;
    }

    private Result<Make> validate(Make m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
