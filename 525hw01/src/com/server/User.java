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
public class User implements UserAPI {

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

    /**
     *
     * @param ticker_name
     * @return
     */
    public StockExchange fetchStock(String ticker_name) {
        for (int i = 0; i < sEList.size(); i++) {
            if ((sEList.get(i)).getTickerName().equals(ticker_name)) {
                return sEList.get(i);
            }
        }
        return null;
    }

    /**
     * get the price for the specific stock in the User's personal stock list.
     *
     * @param ticker_name
     * @return price
     * @return -1 Cannot find the stock.
     *
     */
    @Override
    public double getBoughtPrice(String ticker_name) {
        for (int i = 0; i < sEList.size(); i++) {
            if ((sEList.get(i)).getTickerName().equals(ticker_name)) {
                return sEList.get(i).getPrice();
            }
        }
        return -1;
    }

    /**
     * Return the User's personal stock list.
     *
     * @return
     */
    @Override
    public ArrayList<StockExchange> getStockListofUser() {
        return sEList;
    }

    /**
     *
     * @param ticker_name
     * @param num_stocks
     * @return 0 Successful
     * @return -1 Cannot find the stock
     * @return 1 Balance is not enough
     * @return 2 Stock sold out
     */
    @Override
    public int buy(String ticker_name, int num_stocks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param ticker_name
     * @param num_stocks
     * @return 0 Successful
     * @return -1 Cannot find the stock
     * @return 1 Share is not enough
     */
    @Override
    public int sell(String ticker_name, int num_stocks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * get the price for the specific stock in the STATIC list.
     *
     * @param ticker_name
     * @return price
     * @return -1 Cannot find the stock.
     *
     */
    @Override
    public double getMarketPrice(String ticker_name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}