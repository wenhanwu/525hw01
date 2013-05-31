/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.api.AdminAPI;
import com.api.ServerAPI;
import com.api.UserAPI;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author jingboyu
 */
public class Server implements ServerAPI{

    private static final int PORT = 1099;
    private static Registry registry;
//    private static UserList userList; 
//    private static AdminList adminList;
    
    public Server() {
    }

    public static void main(String args[]) {

        try {
            User userObj = new User();
            Admin adminObj = new Admin("admin");
            UserAPI userStub = (UserAPI) UnicastRemoteObject.exportObject(userObj, 0);
            AdminAPI adminStub = (AdminAPI) UnicastRemoteObject.exportObject(adminObj, 0);
            
            // Bind the remote object's stub in the registry
            registry = LocateRegistry.createRegistry(PORT);
            registry.bind("UserAPI", userStub);
            registry.bind("AdminAPI", adminStub);
            
            UserList.loadUserData();
            StockList.loadStockPoolFromDisk(); //true or false
            
            System.err.println("UserAPI Server ready");

            while (true) {
                StockList.updateWholeStockList();
                Thread.sleep(1000);
                System.out.println(StockList.getStockPool());
//                System.out.println(adminObj.displayMarketStocks());
            }

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

//    @Override
//    public boolean isConnect() throws RemoteException {
//        return true;
//    }

}
