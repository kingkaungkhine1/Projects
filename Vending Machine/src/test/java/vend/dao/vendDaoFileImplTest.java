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
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.util.List;
import com.google.gson.Gson;

/**
 *
 * @author kaung
 */
public class vendDaoFileImplTest
{
    vendDao testDao;
    
    public vendDaoFileImplTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp() throws Exception
    {
        String testFile = "test.json";
        new FileWriter(testFile);
        testDao = new vendDaoFileImpl(testFile);
        testDao.getAllItems().clear();
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void testAddGetItem() throws Exception
    {
        vend item = new vend();
        item.setItemName("Test Item");
        item.setItemCost("0.25");
        item.setNum("3");
        testDao.addItem(item);

        vend retrivedItem = testDao.getItem("Test Item");

        assertEquals(item.getItemName(), retrivedItem.getItemName());
        System.out.println("Checking Item Names");
        assertEquals(item.getItemCost(), retrivedItem.getItemCost());
        System.out.println("Checking Item Costs");
        assertEquals(item.getNum(), retrivedItem.getNum());
        System.out.println("Checking Num of Items");
    }

    @Test
    public void testGetAllItems() throws Exception
    {
        vend item = new vend();
        item.setItemName("Test Item 2");
        item.setItemCost("0.3");
        item.setNum("4");

        vend item2 = new vend();
        item.setItemName("Test Item 3");
        item.setItemCost("0.4");
        item.setNum("5");

        testDao.addItem(item);
        testDao.addItem(item2);
        List<vend> allItems = testDao.getAllItems();

        assertNotNull(allItems);
        System.out.println("The list of items must not null");
        assertEquals(2, allItems.size());
        System.out.println("List of items should have 2 items.");

        assertTrue(testDao.getAllItems().contains(item));
        System.out.println("The list of items should include Test Item 2");

        assertTrue(testDao.getAllItems().contains(item2));
        System.out.println("The list of items should include Test Item 3");
    }
    
    @Test
    public void testRemoveItem() throws Exception
    {
        vend item = new vend();
        item.setItemName("Test Item 4");
        item.setItemCost("0.5");
        item.setNum("6");

        vend item2 = new vend();
        item.setItemName("Test Item 5");
        item.setItemCost("0.6");
        item.setNum("7");

        testDao.addItem(item);
        testDao.addItem(item2);

        int index = testDao.getAllItems().indexOf(item);
        vend itemRemoved = testDao.getAllItems().get(index);
        testDao.removeItem(item);

        assertEquals(item, itemRemoved);
        System.out.println("the removed item is test item 4");

        List<vend> allItems = testDao.getAllItems();
        
        assertNotNull(allItems);
        System.out.println("List of items shouldn't be null");
        assertEquals(allItems.size(), 1);
        System.out.println("List of items should be one");


        assertFalse(allItems.contains(item));
        System.out.println("All items should not contain include test item 4");
        assertTrue(allItems.contains(item2));
        System.out.println("All items should contain test item 5");

        int index2 = testDao.getAllItems().indexOf(item2);
        vend itemRemoved2 = testDao.getAllItems().get(index2);
        testDao.removeItem(item2);

        assertEquals(item2, itemRemoved2);

        System.out.println("Removed item should be test item 5");

        allItems = testDao.getAllItems();
        assertTrue(allItems.isEmpty());
        System.out.println("The retrieved list should be empty");

        vend nullItem = testDao.getItem(itemRemoved.getItemName());
        assertNull(nullItem);
        System.out.println("Test item 4 was removed should be null");

        vend nullItem2 = testDao.getItem(itemRemoved2.getItemName());
        assertNull(nullItem2);
        System.out.println("Test item 5 was removed should be null");
    }
}
