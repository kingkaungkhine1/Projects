/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealershipcapstone.services;

import com.sg.cardealershipcapstone.data.PurchaseRepository;
import com.sg.cardealershipcapstone.models.Purchase;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author emmastout
 */
public class PurchaseServiceTest {
    
    @Autowired
    PurchaseRepository repo;
    
    
    
     
    
    @Test
    public void testSavePurchaseValid() {
    }
    
    @Test
    public void testSavePurchaseLowPurchasePrice() {
    }
    
    @Test
    public void testSavePurchaseNoPhoneOrEmail() {
    }
    
    @Test
    public void testSavePurchaseNoName() {
    }
    
    @Test
    public void testSavePurchaseZipNot5Digits() {
    }
    
    @Test
    public void testSavePurchaseHighPurchasePriceNoCity() {
    }
    
}
