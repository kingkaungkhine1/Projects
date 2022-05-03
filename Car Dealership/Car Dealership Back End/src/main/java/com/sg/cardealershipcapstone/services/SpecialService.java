/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealershipcapstone.services;

import com.sg.cardealershipcapstone.data.SpecialRepository;
import com.sg.cardealershipcapstone.models.Special;
import com.sg.cardealershipcapstone.models.Vehicle;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kaung
 */
@Service
public class SpecialService {

    @Autowired
    SpecialRepository repo;

    public Result<List<Special>> getAllSpecials() {
        Result<List<Special>> result = new Result<>();
        result.setPayload(repo.findAll());
        return result;
    }

    public Result deleteById(int specialId) {
        repo.deleteById(specialId);
        return new Result<>();
    }

    public Result<Special> saveSpecial(Special s) {
        Result<Special> result = validate(s);
        if (result.isSuccess()) {
            s = repo.save(s);
            result.setPayload(s);
        }
        return result;
    }

    private Result<Special> validate(Special s) {
        Result<Special> result = new Result<>();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Special>> errs = validator.validate(s);
        for (ConstraintViolation<Special> err : errs) {
            result.addMessage(err.getMessage());
        }

        return result;
    }
}
