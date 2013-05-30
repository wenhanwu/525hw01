/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.api.AdminAPI;
import java.util.ArrayList;

/**
 *
 * @author w
 */
public class Admin implements AdminAPI {

    private String adminName;

    /**
     * @return the adminName
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * @param adminName the adminName to set
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * Get the Market stock List
     *
     * @return
     */
    @Override
    public ArrayList<StockExchange> getStockList() {
        return StockList.getStockPool();
    }

    /**
     * Find the stock by its name and update it
     *
     * @param ticker_name
     * @param new_price
     * @return
     */
    @Override
    public boolean update(String ticker_name, double new_price) {
        if (StockList.getStockbyName(ticker_name) != null) {
            StockList.update(ticker_name, new_price);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Display all the Market Stocks
     *
     * @return
     */
    @Override
    public int displayMarketStocks() {
        System.out.println("Stock Name-----Shares Hold-----Price of Last Trade");
        for (int i = 0; i < StockList.getStockPool().size(); i++) {
            System.out.println((StockList.getStockPool().get(i)).getTickerName() + "  "
                    + (StockList.getStockPool().get(i)).getShare() + "   "
                    + (StockList.getStockPool().get(i)).getPrice());
        }
        return 0;
    }
}
