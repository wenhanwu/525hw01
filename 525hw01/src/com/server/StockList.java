/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import java.util.ArrayList;

/**
 * This class contains all the stocks on
 * @author haijun
 */
public class StockList {
    private ArrayList<Stock> stockpool;
    private int count;

    public StockList() {
        stockpool = null;
        this.count = 0;
    }
    
    public Stock getStockbyName(String ticker_name) {
        if (stockpool == null)
            return null;
        for (int i = 0; i < count; ++ i) {
            if (stockpool.get(i).isSameStock(ticker_name))
                return stockpool.get(i);
        }
        return null;
    }
    
    
}
