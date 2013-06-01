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

    /**
     * For Admin: update the price of a specific stock
     * @param ticker_name name of the stock needs to be updated
     * @param new_price the updated price
     * @return true if update successful; otherwise return false
     * @throws RemoteException 
     */
    public boolean update(String ticker_name, double new_price) throws RemoteException;

    /**Displaying the list of all the stocks that system maintains
     * @return a formatted list of the stocks
     * @throws RemoteException 
     */
    public String displayMarketStocks() throws RemoteException;

    /**
     * For displaying of the admin name on client 
     * @return admin name
     * @throws RemoteException 
     */
    public String getAdminName() throws RemoteException;
     public void startAdmin(String adminName) throws RemoteException;
}
