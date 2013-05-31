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
    
    public Stock(String ticker_name, double price) {
        this.ticker_name = ticker_name;
        this.price = price;
    }    

    public Stock(Stock newstock) {
        this.ticker_name = newstock.ticker_name;
        this.price = newstock.price;
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

    public synchronized void setPrice(double price) {
        this.price = price;
    }
       
    public boolean isSameStock(String ticker) {
        if (this.ticker_name.equalsIgnoreCase(ticker))
            return true; 
        else 
            return false;
    }
    
    public String toString() {
        return this.getTickerName() + " " + this.getPrice();
    }
}

