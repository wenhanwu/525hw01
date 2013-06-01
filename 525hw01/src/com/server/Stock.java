package com.server;

/**
 * This is the class for Stock entity.
 * @author haijun
 */
public class Stock {
    private String ticker_name;
    private double price; 
    
    /**
     * Constructor.
     * @param ticker_name
     * @param price 
     */
    public Stock(String ticker_name, double price) {
        this.ticker_name = ticker_name;
        this.price = price;
    }    

    /**
     * Copy constructor.
     * @param newstock 
     */
    public Stock(Stock newstock) {
        this.ticker_name = newstock.ticker_name;
        this.price = newstock.price;
    }
    
    /**
     * Return the ticker_name.
     * @return 
     */
    public String getTickerName() {
        return ticker_name;
    }

    /**
     * Set the ticker_name.
     * @param tickerName 
     */
    public void setTickerName(String tickerName) {
        this.ticker_name = tickerName;
    }

    /**
     * Return the price of the stock.
     * @return 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the stock price. Note this is a synchronized method. 
     * @param price 
     */
    public synchronized void setPrice(double price) {
        this.price = price;
    }
       
    /**
     * Check whether two stocks are the same by comparing the ticker_name.
     * @param ticker
     * @return 
     */
    public boolean isSameStock(String ticker) {
        if (this.ticker_name.equalsIgnoreCase(ticker))
            return true; 
        else 
            return false;
    }
    
    /**
     * Return the String of a stock.
     * @return 
     */
    public String toString() {
        return this.getTickerName() + " " + this.getPrice();
    }
}

