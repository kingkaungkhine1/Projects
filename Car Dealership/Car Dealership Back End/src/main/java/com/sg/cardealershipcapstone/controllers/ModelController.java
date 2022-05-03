/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealershipcapstone.controllers;

import com.sg.cardealershipcapstone.models.Model;
import com.sg.cardealershipcapstone.models.Special;
import com.sg.cardealershipcapstone.services.ModelService;
import com.sg.cardealershipcapstone.services.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ModelService service;
    
     @GetMapping("/all")
    public List<Model> getAllModels() {
        return service.getAllModels().getPayload();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Model> addModel(@RequestBody Model m) {
        Result<Model> result = service.saveModel(m);
        if (result.isSuccess()) {
            return ResponseEntity.ok(m);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
