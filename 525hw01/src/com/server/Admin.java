/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.api.AdminAPI;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author w
 */
public class Admin implements AdminAPI {

    private String adminName;

    public Admin(String adminName) {
        this.adminName = adminName;
    }

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
        returnStr += "Stock Name-----Shares Hold-----Current Price\n";
        for (int i = 0; i < StockList.getStockPool().size(); i++) {
            double price = (StockList.getStockPool().get(i)).getPrice();
            BigDecimal changeDP = new BigDecimal(price);
            double priceDisplay = changeDP.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            returnStr += ((StockList.getStockPool().get(i)).getTickerName() + "\t\t"
                    + (StockList.getStockPool().get(i)).getShare() + "\t\t"
                    + priceDisplay + "\n");
        }
        return returnStr;
    }
}
