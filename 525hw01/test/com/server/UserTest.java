/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author w
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUserName method, of class User.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        User instance = null;
        String expResult = "";
        String result = instance.getUserName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUserName method, of class User.
     */
    @Test
    public void testSetUserName() {
        System.out.println("setUserName");
        String userName = "";
        User instance = null;
        instance.setUserName(userName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBalance method, of class User.
     */
    @Test
    public void testGetBalance() {
        System.out.println("getBalance");
        User instance = null;
        double expResult = 0.0;
        double result = instance.getBalance();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBalance method, of class User.
     */
    @Test
    public void testSetBalance() {
        System.out.println("setBalance");
        double balance = 0.0;
        User instance = null;
        instance.setBalance(balance);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fetchStock method, of class User.
     */
    @Test
    public void testFetchStock() {
        System.out.println("fetchStock");
        String ticker_name = "";
        User instance = null;
        StockExchange expResult = null;
        StockExchange result = instance.fetchStock(ticker_name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAvailableShare method, of class User.
     */
    @Test
    public void testGetAvailableShare() {
        System.out.println("getAvailableShare");
        String ticker_name = "aaa";
        User instance = new User("aaa",1.0);
        int expResult = 0;
        int result = instance.getAvailableShares(ticker_name);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        System.out.println(result);
    }

    /**
     * Test of getMarketPrice method, of class User.
     */
    @Test
    public void testGetMarketPrice() {
        System.out.println("getMarketPrice");
        String ticker_name = "";
        User instance = null;
        double expResult = 0.0;
        double result = instance.getMarketPrice(ticker_name);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumShare method, of class User.
     */
    @Test
    public void testGetNumShare() {
        System.out.println("getNumShare");
        String ticker_name = "";
        User instance = null;
        int expResult = 0;
        int result = instance.getNumShare(ticker_name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStockListofUser method, of class User.
     */
    @Test
    public void testGetStockListofUser() {
        System.out.println("getStockListofUser");
        User instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getStockListofUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buy method, of class User.
     */
    @Test
    public void testBuy() {
        System.out.println("buy");
        String ticker_name = "";
        int num_stocks = 0;
        User instance = null;
        int expResult = 0;
        int result = instance.buy(ticker_name, num_stocks);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sell method, of class User.
     */
    @Test
    public void testSell() {
        System.out.println("sell");
        String ticker_name = "";
        int num_stocks = 0;
        User instance = null;
        int expResult = 0;
        int result = instance.sell(ticker_name, num_stocks);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayStocksHold method, of class User.
     */
    @Test
    public void testDisplayStocksHold() {
        System.out.println("displayStocksHold");
        User instance = null;
        String expResult = "";
        String result = instance.displayStocksHold();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of test method, of class User.
     */
    @Test
    public void testTest() {
        System.out.println("test");
        User instance = null;
        String expResult = "";
        String result = instance.test();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}