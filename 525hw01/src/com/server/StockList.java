/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.api.AdminAPI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * This class contains all the stock in a list, and supplies methods to manipulate the list. 
 * @author haijun
 */
public class StockList {
    private static final int INITIAL_SHARES = 1000;
    private static ArrayList<StockExchange> stockPool = new ArrayList<StockExchange>();

    /**
     * Default constructor.
     */
    public StockList() {
        //not used yet.
    }

    /**
     * Return the StockPool (list).
     * @return 
     */
    public static ArrayList<StockExchange> getStockPool() {
        return stockPool;
    }
    
    /**
     * Return a StockExchange object by the ticker_name.
     * @param ticker_name
     * @return 
     */
    public static StockExchange getStockbyName(String ticker_name) {
        if (stockPool.isEmpty())
            return null;
        for (int i = 0; i < stockPool.size(); ++ i) {
            if (stockPool.get(i).isSameStock(ticker_name))
                return stockPool.get(i);
        }
        return null;
    }

    /**
     * Add a new Stock to the StockList, this method is invoked only when the ticker_name is first queried. 
     * @param ticker_name
     * @return 
     */
    public static boolean addNewStock(String ticker_name) {
        Stock newStock = getStockInfoFromYahooFinancial(ticker_name);
        if (newStock.getPrice() != -1) {
            StockExchange st = new StockExchange(StockList.INITIAL_SHARES, newStock);
//            stockPool.add(new StockExchange(StockList.INITIAL_SHARES, newStock));
            stockPool.add(st);
            return true;
        } else 
            return false;      
    }
    
    /**
     * Implement the method update() defined in AdminAPI.java.
     * @param ticker_name
     * @param new_price 
     */
    public static boolean update(String ticker_name, double new_price) {
        if (stockPool.isEmpty()) 
            return false;
        for (int i = 0; i < stockPool.size(); ++ i) {
            if (stockPool.get(i).isSameStock(ticker_name)) {
                stockPool.get(i).setPrice(new_price);
                return true;
            }
        }
        return false;        
    }
    
    /**
     * This method loops through the StockList and update (only) the price of the stocks. 
     */
    public static void updateWholeStockList() {
        if (stockPool.isEmpty())
            return;
        for (int i = 0; i < stockPool.size(); ++ i) {
            stockPool.get(i).overwriteStockPrice(getStockInfoFromYahooFinancial(stockPool.get(i).getTickerName()));
        }
    }
            
    /**
     * This method retrieves Stock information the Yahoo financial service.
     * @param ticker_name
     * @return an object of Stock, contains ticker_name and price.
     */
    public static Stock getStockInfoFromYahooFinancial(String ticker_name) {
        double stockPrice = -1; 
        try {
            URL yahooFinance = new URL("http://finance.yahoo.com/d/quotes.csv?s=" + ticker_name + "&f=g");
            URLConnection yc = yahooFinance.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            if ((inputLine = in.readLine()) != null) {
                stockPrice = Double.parseDouble(inputLine);
            }            
            in.close();
            return new Stock(ticker_name, stockPrice);            
        } catch (IOException ex) {
            System.out.println("Could not get the stock information from the server.");
            return null;
        }
    }
    
    /**
     * This method saves all the information in the stockPool to the disk for persistent storage.
     * @return 
     */
    public static boolean saveStockPoolToDisk() {
        return false; //to-do 
    }
    
    public static boolean loadStockPoolFromDisk() {
        return false; //to-do
    }
}
