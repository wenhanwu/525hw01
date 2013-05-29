/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api;

import com.server.Stock;
import com.server.StockExchange;
import java.lang.*;
import java.util.ArrayList;

/**
 *
 * @author jingboyu
 */
public interface UserAPI {

    public double getBoughtPrice(String ticker_name);

    public int buy(String ticker_name, int num_stocks);

    public int sell(String ticker_name, int num_stocks);

    public ArrayList<StockExchange> getStockListofUser();
    
    public int displayStocksHold();
}
