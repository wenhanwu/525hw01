/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api;

import com.server.Stock;
import com.server.StockExchange;
import java.lang.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author jingboyu
 */
public interface UserAPI extends Remote {
    
    public double getBoughtPrice(String ticker_name) throws RemoteException;

    public int buy(String ticker_name, int num_stocks) throws RemoteException;

    public int sell(String ticker_name, int num_stocks) throws RemoteException;

    public ArrayList<StockExchange> getStockListofUser() throws RemoteException;
    
    public int displayStocksHold() throws RemoteException;

    public String test() throws RemoteException;
}
