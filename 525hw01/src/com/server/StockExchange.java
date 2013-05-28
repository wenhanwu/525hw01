/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

/**
 * This class extends Stock class and is only be used in User class.
 * @author haijun
 */
public class StockExchange extends Stock {
    private int share; 

    public StockExchange(int share, String ticker_name, double price) {
        super(ticker_name, price);
        this.share = share;
    }

    public StockExchange(int share, Stock newstock) {
        super(newstock);
        this.share = share;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }
    
    
}
