/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api;

import com.server.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author jingboyu
 */
public interface AdminAPI extends Remote {

    public boolean update(String ticker_name, double new_price) throws RemoteException;

    public ArrayList<StockExchange> getStockList() throws RemoteException;

    public int displayMarketStocks() throws RemoteException;
}
