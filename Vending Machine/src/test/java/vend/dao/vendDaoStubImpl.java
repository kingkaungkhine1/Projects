/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package vend.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import vend.model.vend;
import vend.service.NoItemInventoryException;
import vend.service.vendDataValidationException;
import vend.service.vendInsufficientFundsException;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kaung
 */
public class vendDaoStubImpl implements vendDao
{
    public vend onlyVend;
    public ArrayList<vend> vendList = new ArrayList<>();


    public vendDaoStubImpl() 
    {
        vendList.clear();
        onlyVend = new vend();
        onlyVend.setItemName("TESTING");
        onlyVend.setItemCost("1.00");
        onlyVend.setNum("55");
    }

    public vendDaoStubImpl(vend tempVend)
    {
        vendList.clear();
        this.onlyVend = tempVend;
    }

    @Override
    public ArrayList<vend> getAllItems() throws vendPersistenceException {
        // TODO Auto-generated method stub
        vendList.add(onlyVend);
        return vendList;
    }

    @Override
    public void addItem(vend item) throws vendPersistenceException {
        // TODO Auto-generated method stub
        vendList.add(item);
    }

    @Override
    public vend getItem(String itemName) throws vendPersistenceException {
        // TODO Auto-generated method stub
        if(onlyVend.equals(itemName))
        {
            return onlyVend;
        }
        else
        return null;
    }

    @Override
    public void removeItem(vend item) throws vendPersistenceException {
        // TODO Auto-generated method stub
        //auditDao.writeAuditEntry("ITEM " + item.getItemName() + " REMOVED.");
        //dao.removeItem(item);
        vendList.remove(item);
    }


    @Override
    public void setList(ArrayList<vend> temp) throws vendPersistenceException {
    }

    @Override
    public void save() throws vendPersistenceException{
    }

    @Override
    public void load() throws vendPersistenceException {
    }

    @Override
    public void numUpdate(vend item, String num) throws vendPersistenceException {

    }

    @Override
    public String getChange() {
    //     // TODO Auto-generated method stub
    //     BigDecimal quarter = BigDecimal.valueOf(Change.valueOf("QUARTER").value());
    //     BigDecimal dime = BigDecimal.valueOf(Change.valueOf("DIME").value());
    //     BigDecimal nickel = BigDecimal.valueOf(Change.valueOf("NICKEL").value());
    //     BigDecimal penny = BigDecimal.valueOf(Change.valueOf("PENNY").value());

    //     quarter = currentChange.divide(quarter, RoundingMode.FLOOR).setScale(2);
    //     currentChange = currentChange.remainder(new BigDecimal("0.25"));
    //     dime = currentChange.divide(dime, RoundingMode.FLOOR).setScale(2);
    //     currentChange = currentChange.remainder(new BigDecimal("0.10"));
    //     nickel = currentChange.divide(nickel, RoundingMode.FLOOR).setScale(2);
    //     currentChange = currentChange.remainder(new BigDecimal("0.05"));
    //     penny = currentChange.divide(penny, RoundingMode.FLOOR).setScale(2);
    //     currentChange = currentChange.remainder(new BigDecimal("0.01"));

    //     return "Quarters: " + quarter.setScale(0, RoundingMode.FLOOR).setScale(2).intValue() + "\nDime: " + dime.setScale(0, RoundingMode.FLOOR).setScale(2).intValue() + "\nNickel: " + nickel.setScale(0, RoundingMode.FLOOR).setScale(2).intValue() + "\nPenny: " + penny.setScale(0, RoundingMode.FLOOR).setScale(2).intValue();
        return null;
    }


    @Override
    public void setChange(BigDecimal money) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void clearItems()
    {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String changeToString(String change) {
        // TODO Auto-generated method stub
        return null;
    }
}
