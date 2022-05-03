/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealershipcapstone.controllers;

import com.sg.cardealershipcapstone.models.ReportCriteria;
import com.sg.cardealershipcapstone.models.Special;
import com.sg.cardealershipcapstone.services.ReportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kaung
 */
@CrossOrigin
@RestController
@RequestMapping("/reports")
public class ReportController {
    
//    @Autowired
//    private ReportService service;
//    
//    THESE BOTH NEED FIXING
    // What should I put in the return spot? Will I be returning a report (in which case
    //I need to make a report class
//    
//    @GetMapping("/inventory")
//    public List<Special> getInventoryReport(ReportCriteria criteria) {
//        return service.getInventoryReport(criteria).getPayload();
//    }
//    
//    @GetMapping("/sales")
//    public List<Special> getSalesReport() {
//        return service.getSalesReport().getPayload();
//    }
}
