package com.server;

/**
 * This class extends Stock class and used in User and StockList classes.
 * @author haijun
 */
public class StockExchange extends Stock {
    private int share; 

    /**
     * Constructor.
     * @param share
     * @param ticker_name
     * @param price 
     */
    public StockExchange(int share, String ticker_name, double price) {
        super(ticker_name, price);
        this.share = share;
    }

    /**
     * Constructor by a stock.
     * @param share
     * @param newstock 
     */
    public StockExchange(int share, Stock newstock) {
        super(newstock);
        this.share = share;
    }

    /**
     * Return the shares.
     * @return 
     */
    public int getShare() {
        return share;
    }

    /**
     * Set the shares.
     * @param share 
     */
    public void setShare(int share) {
        this.share = share;
    }
    
    /**
     * Update stock price, used by the admin.
     * @param newStock 
     */
    public void overwriteStockPrice(Stock newStock) {
        if (newStock == null)
            return;
        this.setPrice(newStock.getPrice());
    }
    
    /**
     * Return the string of a StockExchange object.
     * @return 
     */
    public String toString() {
        return super.toString() + " " + this.getShare();
    }
}
