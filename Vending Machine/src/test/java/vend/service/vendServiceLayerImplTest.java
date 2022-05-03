/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this
 * license Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit
 * this template
 */
package vend.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import vend.dao.vendAuditDao;
import vend.dao.vendDao;
import vend.dao.vendDaoStubImpl;
import vend.dao.vendPersistenceException;
import vend.model.vend;
import static org.junit.Assert.*;
import vend.service.vendServiceLayer;

/**
 *
 * @author kaung
 */

public class vendServiceLayerImplTest {
    vendDao dao = new vendDaoStubImpl();
    vendAuditDao auditDao = new vendAuditDaoStubImpl();
    private vendServiceLayer service = new vendServiceLayerImpl(dao, auditDao);

    public vendServiceLayerImplTest() {}

    @BeforeClass
    public static void setUpClass() {}

    @AfterClass
    public static void tearDownClass() {}

    @Before
    public void setUp() {}

    @After
    public void tearDown() {}

    @Test
    public void testCreateValidVendItem() {
        vend item = new vend();
        item.setItemName("TEST 2");
        item.setItemCost("1.00");
        item.setNum("3");
        try
        {
            service.addItem(item);
            service.removeItem(item);
        } catch (NoItemInventoryException | vendInsufficientFundsException
                | vendPersistenceException | vendDataValidationException e)
        {
            // TODO Auto-generated catch block
            fail("Item was valid there should be no exception thrown");
        }
    }

    @Test
    public void testCreateInvalidVendItem() throws Exception {
        vend item = new vend();
        // item.setItemName("TEST 3");
        item.setItemCost("2.00");
        item.setNum("4");
        try
        {
            service.addItem(item);
            service.removeItem(item);
            fail("vendDataValidationException not thrown");
        } catch (NoItemInventoryException | vendInsufficientFundsException
                | vendPersistenceException e)
        {
            // TODO Auto-generated catch block
            fail("incorrect exception thrown");
        } catch (vendDataValidationException e)
        {
            return;
        }
    }

    @Test
    public void testGetAllItems() throws Exception {
        service.clearList();
        service.getAllItems().clear();
        ArrayList<vend> temp = service.getAllItems();
        // for(vend d : temp)
        // {
        // service.removeItem(d);
        // }
        temp.clear();
        vend item = new vend("TEST 3", "3.00", "4");
        temp.add(item);
        assertEquals(1, temp.size());
        System.out.println("Should have one item");
        assertTrue(service.getAllItems().contains(item));
        System.out.println("Should contain this item TEST 3");
    }

    @Test
    public void testNoItem() {
        vend item = new vend("Nothing", "3.00", "0");
        try
        {
            service.addItem(item);
            if (Integer.parseInt(item.getNum()) == 0)
            {
                //throw new NoItemInventoryException("testing is correct");
            } else
            {
                fail("vendDataValidationException not thrown");
            }

        } catch (NoItemInventoryException ex)
        {
            return;
        } catch (vendInsufficientFundsException | vendPersistenceException
                | vendDataValidationException ex)
        {
            fail("wrong exception thrown");
        }
    }

    @Test
    public void testInsufficentFunds() {
        vend item = new vend("No money?", "1.00", "1");
        BigDecimal money = new BigDecimal("0.50");
        try
        {
            // money == user gained money
            // itemCosts == selected item costs
            // if money >= itemCosts
            // itemCosts <= money

            // firstBigDecimal.compareTo(secondBigDecimal) <= 0 // "<="
            service.addItem(item);
            BigDecimal itemCost = new BigDecimal(item.getItemCost());
            if (!(itemCost.compareTo(money) <= 0))
            {
                //money = money.subtract(itemCost);
                // System.out.println(money.doubleValue());
                //service.setChange(money);
                // System.out.println(money.subtract(temp) + "");
                //service.getItem(item.getItemName()).setItemCost(money + "");
                //updateInventory(item);
                //throw new vendInsufficientFundsException("testing is correct");
            } 
            else
            {
                fail("should be not enough funds");
            }
        } catch (vendInsufficientFundsException ex)
        {
            return;
        } catch (NoItemInventoryException | vendPersistenceException | vendDataValidationException ex)
        {
            fail("wrong exception");
        } 
    }
}
