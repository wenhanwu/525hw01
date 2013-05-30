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

//    /**
//     * Get the Market stock List
//     *
//     * @return
//     */
//    @Override
//    public ArrayList<StockExchange> getStockList() {
//        return StockList.getStockPool();
//    }
    /**
     * Find the stock by its name and update it
     *
     * @param ticker_name
     * @param new_price
     * @return true Successful
     * @return false Cannot find in the list
     */
    @Override
    public boolean update(String ticker_name, double new_price) {
        return StockList.update(ticker_name, new_price);
    }

    /**
     * Display all the Market Stocks
     *
     * @return
     */
    @Override
    public String displayMarketStocks() {
        String returnStr = "";
        returnStr += "Stock Name-----Shares Hold-----Price of Last Trade\n";
        for (int i = 0; i < StockList.getStockPool().size(); i++) {
            returnStr += ((StockList.getStockPool().get(i)).getTickerName() + "  "
                    + (StockList.getStockPool().get(i)).getShare() + "   "
                    + (StockList.getStockPool().get(i)).getPrice() + "\n");
        }
        return returnStr;
    }
}
