/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealershipcapstone.models;

import java.math.BigDecimal;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author kaung
 */
@Data
public class VehicleSearchCriteria {

    
    private String makeModelYear;
    private Integer minYear;
    private Integer maxYear;
    private BigDecimal minSalePrice;
    private BigDecimal maxSalePrice;
    
}
