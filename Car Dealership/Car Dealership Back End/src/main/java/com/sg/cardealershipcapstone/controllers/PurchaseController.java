/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealershipcapstone.controllers;

import com.sg.cardealershipcapstone.models.Purchase;
import com.sg.cardealershipcapstone.services.PurchaseService;
import com.sg.cardealershipcapstone.services.Result;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/purchase")
public class PurchaseController {
    
    @Autowired
    private PurchaseService service;
    
    @PostMapping
    public ResponseEntity<Object> addPurchase(@Valid @RequestBody Purchase p,  BindingResult br) {
        if (br.hasErrors()) {
            
        } 
        
        Result<Purchase> result = service.savePurchase(p);
        if (result.isSuccess()) {
            return ResponseEntity.ok(p);
        } else {
            return ResponseEntity.badRequest().body(result.getMessages());
        }
    }
}
