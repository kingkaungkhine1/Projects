/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealershipcapstone.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author kaung
 */
@Data
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    
    @Size(min=1, max=40)
    private String firstName;
    
    @Size(min=1, max=40)
    private String lastName;
    
    @Size(min=1, max=100)
    @Email
    private String email;
    
    @Size(min=1, max=20)
    private String password;
    private String role;
}
