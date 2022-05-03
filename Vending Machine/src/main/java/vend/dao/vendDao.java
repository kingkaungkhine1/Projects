/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vend.dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import vend.model.vend;

/**
 *
 * @author kaung
 */
public interface vendDao
{
    public ArrayList<vend> getAllItems() throws vendPersistenceException;

    
    public void addItem(vend item) throws vendPersistenceException;

    public vend getItem(String itemName) throws vendPersistenceException;

    public void removeItem(vend item) throws vendPersistenceException;

    public void setList(ArrayList<vend> temp) throws vendPersistenceException;

    public void numUpdate(vend item, String num) throws vendPersistenceException;
 
    public void save() throws vendPersistenceException; 

    public void load() throws vendPersistenceException;

    public String getChange();

    public void setChange(BigDecimal money);
    
    public void clearItems() throws vendPersistenceException;

    public String changeToString(String change);
}
