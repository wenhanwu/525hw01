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
 * @author wenhanwu
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
     * Test of buy method, of class User.
     */
//    @Test
//    public void testBuy() {
//        System.out.println("buy");
//        String ticker_name = "";
//        int num_stocks = 0;
//        User instance = null;
//        int expResult = 0;
//        int result = instance.buy(ticker_name, num_stocks);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of sell method, of class User.
     */
    @Test
    public void testSell() {
        System.out.println("sell");
        StockList.loadStockPoolFromDisk();
        UserList.loadUserData();
        String ticker_name = "GOOG";
        int num_stocks = 22;
        User instance = UserList.fetchByUserName("kate");
//        int expResult = 0;
        System.out.println(instance.getBalance());
        System.out.println(instance.displayStocksHold());
        int result = instance.sell(ticker_name, num_stocks);
        System.out.println(instance.getBalance());
        System.out.println(instance.displayStocksHold());
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}