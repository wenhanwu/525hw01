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
 * @author haijun
 */
public class StockListTest {
    
    public StockListTest() {
        
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
     * Test of getStockPool method, of class StockList.
     */
//    @Test
//    public void testGetStockPool() {
//        System.out.println("getStockPool");
//        ArrayList expResult = null;
//        ArrayList result = StockList.getStockPool();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//
//    }

    /**
     * Test of getStockbyName method, of class StockList.
     */
    @Test
    public void testGetStockbyName() {
        System.out.println("getStockbyName");
        String ticker_name = "ABAD";
//        StockExchange expResult = null;
        StockExchange result = null; 
        StockList.loadStockPoolFromDisk();
            result = StockList.getStockbyName(ticker_name);
        System.out.println(result);
        System.out.println(StockList.getStockPool());
        System.out.println(StockList.update("AAAA", 10));
        System.out.println(StockList.getStockPool());
        StockList.update("BBRY", 1);
        System.out.println(StockList.getStockPool());
        
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of addNewStock method, of class StockList.
     */
//    @Test
//    public void testAddNewStock1() {
//        System.out.println("addNewStock");
//        String ticker_name = "GOOG";
////        boolean expResult = false;
//        if (StockList.addNewStock(ticker_name))
//            System.out.println(StockList.getStockPool().toString());
//        else 
//            System.out.println("fail");
//    }
//        /**
//     * Test of update method, of class StockList.
//     */
////    @Test
////    public void testUpdate() {
////        System.out.println("update");
////        String ticker_name = "GOOG";
////        double new_price = 100;
//////        boolean expResult = false;
////        if (StockList.update(ticker_name, new_price))
////            System.out.println(StockList.getStockPool());
////        else
////            System.out.println("fail.");
////
////    }
//    @Test
//    public void testAddNewStock2() {
//        System.out.println("addNewStock");
//        String ticker_name = "YHOO";
////        boolean expResult = false;
//        if (StockList.addNewStock(ticker_name))
//            System.out.println(StockList.getStockPool().toString());
//        else 
//            System.out.println("fail");
//    }
//    @Test
//    public void testAddNewStock3() {
//        System.out.println("addNewStock");
//        String ticker_name = "AAPL";
////        boolean expResult = false;
//        if (StockList.addNewStock(ticker_name))
//            System.out.println(StockList.getStockPool().toString());
//        else 
//            System.out.println("fail");
//    }
//    @Test
//    public void testAddNewStock4() {
//        System.out.println("addNewStock");
//        String ticker_name = "MSFT";
////        boolean expResult = false;
//        if (StockList.addNewStock(ticker_name))
//            System.out.println(StockList.getStockPool().toString());
//        else 
//            System.out.println("fail");
//    }
//    @Test
//    public void testAddNewStock5() {
//        System.out.println("addNewStock");
//        String ticker_name = "BBRY";
////        boolean expResult = false;
//        if (StockList.addNewStock(ticker_name))
//            System.out.println(StockList.getStockPool().toString());
//        else 
//            System.out.println("fail");
//    }



    /**
     * Test of getStockInfoFromYahooFinancial method, of class StockList.
     */
//    @Test
//    public void testGetStockInfoFromYahooFinancial() {
//        System.out.println("getStockInfoFromYahooFinancial");
//        String ticker_name = "GOOG";
////        Stock expResult = null;
//        Stock result = StockList.getStockInfoFromYahooFinancial(ticker_name);
//        System.out.println(result);
//    }




    /**
     * Test of updateWholeStockList method, of class StockList.
//     */
//    @Test
//    public void testUpdateWholeStockList() {
//        System.out.println("updateWholeStockList");
//        StockList.update("GOOG", 100);
//        StockList.update("AAPL", 100);
//        StockList.update("MSFT", 100);
//        StockList.update("BBRY", 100);
//        StockList.getStockbyName("AAPL").setShare(900);
//        
//        System.out.println(StockList.getStockPool());
//        StockList.updateWholeStockList();
//        System.out.println(StockList.getStockPool());
//    }

   

    /**
     * Test of loadStockPoolFromDisk method, of class StockList.
     */
//    @Test
//    public void testSaveStockPoolToDisk() {
//        System.out.println("saveStockPoolToDisk");
////        boolean expResult = false;
//        System.out.println(StockList.getStockPool());
//        System.out.println( StockList.saveStockPoolToDisk());
//
//        
//    }

//    @Test
//    public void testLoadStockPoolFromDisk() {
//        System.out.println("loadStockPoolFromDisk");
////        boolean expResult = false;
////        System.out.println(StockList.loadStockPoolFromDisk());
//        StockList.loadStockPoolFromDisk();
//        System.out.println(StockList.getStockPool());
////        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
//        
//    }
}