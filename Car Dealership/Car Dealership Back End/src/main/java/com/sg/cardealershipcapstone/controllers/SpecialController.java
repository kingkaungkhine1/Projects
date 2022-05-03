/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealershipcapstone.controllers;

import com.sg.cardealershipcapstone.models.Special;
import com.sg.cardealershipcapstone.models.Vehicle;
import com.sg.cardealershipcapstone.services.Result;
import com.sg.cardealershipcapstone.services.SpecialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin
@RestController
@RequestMapping("/special")
public class SpecialController {

    @Autowired
    private SpecialService service;

    @GetMapping("/all")
    public List<Special> getAllSpecials() {
        return service.getAllSpecials().getPayload();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecial(@PathVariable int specialId) {
        Result result = service.deleteById(specialId);
        if (result.isSuccess()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Special> addSpecial(@RequestBody Special s) {
        Result<Special> result = service.saveSpecial(s);
        if (result.isSuccess()) {
            return ResponseEntity.ok(s);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
