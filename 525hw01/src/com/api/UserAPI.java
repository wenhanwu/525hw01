/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api;
import com.server.*;

/**
 *
 * @author jingboyu
 */
public interface UserAPI {
    
    	public double getPrice(String ticker_name);
	public boolean buy(String ticker_name, int num_stocks);
	public boolean sell(String ticker_name, int num_stocks);
        public StockList getStockList();
    
}
