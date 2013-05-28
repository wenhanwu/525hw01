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

    private int adminID;
    private String adminName;

    /**
     * @return the adminID
     */
    public int getAdminID() {
        return adminID;
    }

    /**
     * @param adminID the adminID to set
     */
    public void setAdminID(int adminID) {
        this.adminID = adminID;
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

    @Override
    public ArrayList<StockExchange> getStockList() {
        return StockList.getStockPool();
    }

    @Override
    public boolean update(String ticker_name, double new_price) {
        if (StockList.getStockbyName(ticker_name) != null) {
            StockList.update(ticker_name, new_price);
            return true;
        } else {
            return false;
        }

    }
}
