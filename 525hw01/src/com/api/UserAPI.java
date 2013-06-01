package com.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface defines the remote methods for Users at the client side.
 * @author jingboyu
 */
public interface UserAPI extends Remote {

    /**
     * For user to purchase stocks
     * @param ticker_name  name of the stock to buy
     * @param num_stocks number of shares to buy
     * @return -1 Cannot find the stock in Yahoo Finance; 1 Share in the Market is not enough for buying; 2 Balance the user has is not enough
     * @throws RemoteException 
     */
    public int buy(String ticker_name, int num_stocks) throws RemoteException;

    /**
     * For user to sell stocks
     * @param ticker_name the name of the stock to sell
     * @param num_stocks number of shares to sell
     * @return 0 Successful; -1 Cannot find the stock in the current User's account; 1 Share is not enough
     * @throws RemoteException 
     */
    public int sell(String ticker_name, int num_stocks) throws RemoteException;

    /**
     * Get a specific stock's current marketing price
     * @param ticker_name name of the stock
     * @return price of the stock user queried
     * @throws RemoteException 
     */
    public double getMarketPrice(String ticker_name) throws RemoteException;

    /**
     * Get number of shares of a stock that is currently holding by a user
     * @param ticker_name name of the stock
     * @return number of shares
     * @throws RemoteException 
     */
    public int getNumShare(String ticker_name) throws RemoteException;

    /**
     * Get user balance
     * @return balance of the user
     * @throws RemoteException 
     */
    public double getUserBalance() throws RemoteException;

    /**
     * Get the number of shares of a specific stock that is available for trading
     * @param ticker_name name of the stock
     * @return number of shares available
     * @throws RemoteException 
     */
    public int getAvailableShares(String ticker_name) throws RemoteException;

    /**
     * For displaying the stock list of a specified user
     * @return a formatted list of the stocks information
     * @throws RemoteException 
     */
    public String displayStocksHold() throws RemoteException;

    /**
     * Get current user
     * @param user_name user name
     * @throws RemoteException 
     */
    public void populateCurrentUser(String user_name) throws RemoteException;

    /**
     * Save user information to local disk
     * @throws RemoteException 
     */
    public void saveUserListToDisk() throws RemoteException;

    /**
     * Get current user name
     * @return user name
     * @throws RemoteException 
     */
    public String getUserName() throws RemoteException;
    
    /**
     * Release the connected user
     * @throws RemoteException 
     */
    public void freeCurrentUser() throws RemoteException;
}
