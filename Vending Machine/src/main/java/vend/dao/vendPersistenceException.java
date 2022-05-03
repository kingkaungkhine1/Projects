/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vend.dao;

import vend.service.NoItemInventoryException;
import vend.service.vendDataValidationException;
import vend.service.vendInsufficientFundsException;
import vend.service.vendServiceLayerImpl;

/**
 *
 * @author kaung
 */
public class vendPersistenceException extends Exception
{
    public vendPersistenceException(String message){
        super(message);
    }
    
     public vendPersistenceException(String message, Throwable cause){
        super(message, cause);
    }
}
