/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.api.UserAPI;
import java.util.ArrayList;

/**
 *
 * @author w
 */
public class User implements UserAPI{

    private int userID;
    private String userName;
    private int balance;
    private ArrayList<StockExchange> sEList = new ArrayList();

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @param ticker_name
     * @return
     */
    public int ifHasStock(String ticker_name) {
        for (int i = 0; i < sEList.size(); i++) {
            if ((sEList.get(i)).getTickerName().equals(ticker_name)) {
                return sEList.get(i).getShare();
            }
        }
        return 0;
    }
    
    /**
     * @return the balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public double getPrice(String ticker_name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Stock getStockList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean buy(String ticker_name, int num_stocks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean sell(String ticker_name, int num_stocks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}