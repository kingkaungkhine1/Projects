/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealershipcapstone.models;

import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author kaung
 */
@Data
public class ReportCriteria {
    
    private User user;
    private LocalDate minDate;
    private LocalDate maxDate;
}
