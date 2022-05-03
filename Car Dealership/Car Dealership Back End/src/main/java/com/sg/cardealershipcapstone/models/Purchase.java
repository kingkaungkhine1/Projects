/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealershipcapstone.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author kaung
 */
@Data
@Entity
public class Purchase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseId;
    
    @Size(min = 1, message = "Name is required.")
    @Size(max = 50, message = "Name is too long (50 characters max).")
    private String name;
    private String phone;
    

    @Email(message="Please enter a valid email address.")
    private String email;
    
    @Size(min = 1, message = "Address is required.")
    @Size(max = 100, message = "Address is too long (100 characters max).")
    private String street1;
    private String street2;
    
    @Size(min = 1, message = "City is required.")
    @Size(max = 100, message = "City name is too long (100 characters max).")
    private String city;
    
    @Size(min = 2, max= 2, message = "State is required.")
    private String state;
    
    @Size(min = 5, max= 5, message = "Zipcode is required and must be 5 digits.")
    private String zipCode;
    
    
    private LocalDate purchaseDate = LocalDate.now();
    
    @Size(min = 1, message = "Purchase Type is required.")
    private String purchaseType;
    
    @ManyToOne
    @JoinColumn(name = "UserId")
    private User user;
    
    @OneToOne
    @JoinColumn(name = "VehicleId")
    private Vehicle vehicle;
    
    private BigDecimal purchasePrice;

}
