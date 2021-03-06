package com.server;

import com.api.UserAPI;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This is the User Obj class to contain all the data for the User. The User can
 * buy, sell, list stocks.
 *
 * @author wenhanwu
 */
public class User implements UserAPI {

    //Start with 1000 for the balance
    private final double INITIAL_BALANCE = 1000;
    //User name
    private String userName;
    //User's balance
    private double balance;
    //Array list to store the stocks bought by the user
    private ArrayList<StockExchange> sEList;

    /**
     * Constructor
     */
    public User() {
        this.userName = null;
    }

    /**
     * Constructor with the user name
     *
     * @param userName
     */
    public User(String userName) {
        this.userName = userName;
        this.balance = this.INITIAL_BALANCE;
        this.sEList = new ArrayList<StockExchange>();
    }

    /**
     * Constructor.
     * @param userName
     * @param balance
     * @param sEList
     */
    public User(String userName, double balance, ArrayList<StockExchange> sEList) {
        this.userName = userName;
        this.balance = balance;
        this.sEList = sEList;
    }

    /**
     * Copy constructor.
     * @param user 
     */
    public User(User user) {
        this.userName = user.getUserName();
        this.balance = user.getBalance();
        this.sEList = user.getStockListofUser();
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     *
     * @param ticker_name
     * @return
     */
    public StockExchange fetchStock(String ticker_name) {
        if (!sEList.isEmpty()) {
            for (int i = 0; i < sEList.size(); i++) {
                if ((sEList.get(i)).getTickerName().equals(ticker_name)) {
                    return sEList.get(i);
                }
            }
        }
        return null;
    }

    /**
     * Get the available shares in the market for the specific stock.
     *
     * @param ticker_name
     * @return int Share
     * @return -1 Cannot find the stock in the market
     *
     */
    public int getAvailableShares(String ticker_name) {
        StockExchange stockEx = StockList.getStockbyName(ticker_name);
        if (stockEx == null) {
            return -1;
        } else {
            return stockEx.getShare();
        }
    }

    /**
     * Get the market price for the specific stock.
     *
     * @param ticker_name
     * @return double price
     * @return -1 Cannot find the stock in the market
     *
     */
    public double getMarketPrice(String ticker_name) {
        StockExchange stockEx = StockList.getStockbyName(ticker_name);
        if (stockEx == null) {
            return -1.0;
        } else {
            return stockEx.getPrice();
        }
    }

    /**
     * Get the shared number for the specific stock.
     *
     * @param ticker_name
     * @return int shared number for the specific stock
     * @return -1 Cannot find the stock in the market
     *
     */
    public int getNumShare(String ticker_name) {
        for (int i = 0; i < sEList.size(); i++) {
            if ((sEList.get(i)).getTickerName().equals(ticker_name)) {
                return sEList.get(i).getShare();
            }
        }
        return -1;
    }

    /**
     * Return the User's personal stock list.
     *
     * @return
     */
    public ArrayList<StockExchange> getStockListofUser() {
        return sEList;
    }

    /**
     *
     * @param ticker_name
     * @param num_stocks
     * @return 0 Successful
     * @return -1 Cannot find the stock in Yahoo Finance
     * @return 1 Share in the Market is not enough for buying
     * @return 2 Balance the user has is not enough
     */
    @Override
    public int buy(String ticker_name, int num_stocks) {
        if (StockList.getStockbyName(ticker_name) != (null)) {
            if (StockList.getStockbyName(ticker_name).getShare() >= num_stocks) {
                if (StockList.getStockbyName(ticker_name).getPrice() * num_stocks <= getBalance()) {
                    //Update the share in user's list
                    if (fetchStock(ticker_name) == null) {
                        sEList.add(new StockExchange(0, ticker_name, 0));
                    }
                    fetchStock(ticker_name).setShare(fetchStock(ticker_name).getShare() + num_stocks);
                    //Update the price in user's list
                    fetchStock(ticker_name).setPrice(StockList.getStockbyName(ticker_name).getPrice());
                    //Update the share in Market's list
                    StockList.getStockbyName(ticker_name).setShare(StockList.getStockbyName(ticker_name).getShare() - num_stocks);
                    this.balance = this.balance - StockList.getStockbyName(ticker_name).getPrice() * num_stocks;
                    return 0;//Successful
                } else {
                    return 2;
                }
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }

    /**
     *
     * @param ticker_name
     * @param num_stocks
     * @return 0 Successful
     * @return -1 Cannot find the stock in the current User's account
     * @return 1 Share is not enough
     */
    @Override
    public int sell(String ticker_name, int num_stocks) {
        if (fetchStock(ticker_name) != (null)) {
            if (fetchStock(ticker_name).getShare() >= num_stocks) {
                //Update the share in user's list
                fetchStock(ticker_name).setShare(fetchStock(ticker_name).getShare() - num_stocks);
                //Update the price in user's list
                fetchStock(ticker_name).setPrice(StockList.getStockbyName(ticker_name).getPrice());
                //Update the share in Market's list
                StockList.getStockbyName(ticker_name).setShare(StockList.getStockbyName(ticker_name).getShare() + num_stocks);
                this.balance = this.balance + StockList.getStockbyName(ticker_name).getPrice() * num_stocks;
                return 0;//Successful
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }

    /**
     * Display the stock the User has
     *
     * @return
     */
    @Override
    public String displayStocksHold() {
        String returnStr = "";
        returnStr += "[Ticker_name]\t[Shares]  [Last_Trade_Price]\n";
        for (int i = 0; i < sEList.size(); i++) {
            double price = (sEList.get(i)).getPrice();
            BigDecimal changeDP = new BigDecimal(price);
            double priceDisplay = changeDP.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            returnStr += ((sEList.get(i)).getTickerName() + "\t\t"
                    + (sEList.get(i)).getShare() + "\t\t"
                    + priceDisplay + "\n");
        }
        return returnStr;
    }

    /**
     * Get the user's balance for remote use
     *
     * @return the balance
     * @throws RemoteException
     */
    @Override
    public double getUserBalance() throws RemoteException {
        return this.balance;
    }

    /**
     * Build the data for the user
     *
     * @param userName user's name
     * @throws RemoteException
     */
    public void populateCurrentUser(String userName) throws RemoteException {
        User tempUser = UserList.fetchByUserName(userName);
        this.userName = tempUser.getUserName();
        this.balance = tempUser.getBalance();
        this.sEList = tempUser.getStockListofUser();
    }

    /**
     * Save the data to the data file
     *
     * @throws RemoteException
     */
    public void saveUserListToDisk() throws RemoteException {
        ArrayList<User> tempList = new ArrayList<User>();
        tempList.add(new User(this));
        UserList.syncUserList(tempList);
        UserList.saveUserData();
    }

    /**
     * Reset the data for the current user, to free the position
     *
     * @throws RemoteException
     */
    public void freeCurrentUser() throws RemoteException {
        this.userName = null;
        this.balance = 0;
        this.sEList = null;
    }
}
