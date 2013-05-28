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
public interface AdminAPI {
    
    	public boolean update(String ticker_name, double new_price);
        public StockList getStockList();
}
