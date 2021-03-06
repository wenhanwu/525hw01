package com.server;

import com.api.AdminAPI;
import java.math.BigDecimal;
import java.rmi.RemoteException;

/**
 * This is the Admin Obj class to contain all the data for the Admin. The Admin
 * can update, list the stock information
 *
 * @author wenhanwu
 */
public class Admin implements AdminAPI {

    //Admin's name
    private String adminName;

    /**
     *
     * Constructor
     */
    public Admin() {
    }

    /**
     * Constructor
     *
     * @param name
     */
    public Admin(String adminName) {
        this.adminName = adminName;
    }

    /**
     * @return the adminName
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * @param adminName the adminName to set
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * Find the stock by its name and update it
     *
     * @param ticker_name
     * @param new_price
     * @return true Successful
     * @return false Cannot find in the list
     */
    @Override
    public boolean update(String ticker_name, double new_price) {
        return StockList.update(ticker_name, new_price);
    }

    /**
     * Display all the Market Stocks
     *
     * @return
     */
    @Override
    public String displayMarketStocks() {
        String returnStr = "";
        returnStr += "[Ticker_name]\t[Shares]\t[Current_Price]\n";
        for (int i = 0; i < StockList.getStockPool().size(); i++) {
            double price = (StockList.getStockPool().get(i)).getPrice();
            //keep two places of decimal
            BigDecimal changeDP = new BigDecimal(price);
            double priceDisplay = changeDP.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            returnStr += ((StockList.getStockPool().get(i)).getTickerName() + "\t\t"
                    + (StockList.getStockPool().get(i)).getShare() + "\t\t"
                    + priceDisplay + "\n");
        }
        return returnStr;
    }

    /**
     * Pass and set name for admin, for the remote uses
     *
     * @param adminName
     * @throws RemoteException
     */
    @Override
    public void startAdmin(String adminName) throws RemoteException {
        this.adminName = adminName;
    }
}
