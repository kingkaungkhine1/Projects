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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author kaung
 */
@Data
@Entity
public class Special {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int specialId;
    
    @Size(min=1, max=100)
    private String title;
    
    @Size(min=1, max=1000)
    private String description;
}
