/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

/**
 * This is the class for Stock entity.
 * @author haijun
 */
public class Stock {
    private String ticker_name;
    private double price; 

    public Stock(String ticker_name) {
        this.ticker_name = ticker_name;
    }    
    
    public Stock(String ticker_name, double price) {
        this.ticker_name = ticker_name;
        this.price = price;
    }    

    public String getTickerName() {
        return ticker_name;
    }

    public void setTickerName(String tickerName) {
        this.ticker_name = tickerName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
       
    public boolean isSameStock(String ticker) {
        if (this.ticker_name.equals(ticker))
            return true; 
        else 
            return false;
    }
}

