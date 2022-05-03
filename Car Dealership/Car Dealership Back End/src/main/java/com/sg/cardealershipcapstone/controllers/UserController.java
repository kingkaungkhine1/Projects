/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.sg.cardealershipcapstone.controllers;

import com.sg.cardealershipcapstone.models.Special;
import com.sg.cardealershipcapstone.models.User;
import com.sg.cardealershipcapstone.models.Vehicle;
import com.sg.cardealershipcapstone.services.Result;
import com.sg.cardealershipcapstone.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/user")
public class UserController {

   @Autowired
   private UserService service;

   @GetMapping("/all")
   public List<User> getAllUsers() {
       return service.getAllUsers().getPayload();
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public ResponseEntity<User> addUser(@RequestBody User u) {
       Result<User> result = service.saveUser(u);
       if (result.isSuccess()) {
           return ResponseEntity.ok(u);
       } else {
           return ResponseEntity.noContent().build();
       }
   }

   @PutMapping("/{id}")
   public ResponseEntity<Void> editUser(@PathVariable int userId, @RequestBody User u) {

       if (u.getUserId() <= 0 || userId <= 0 || userId != u.getUserId()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }

       Result<User> result = service.saveUser(u);
       if (result.isSuccess()) {
           return ResponseEntity.ok().build();
       } else {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }

}
