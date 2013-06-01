/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.api.AdminAPI;
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
public class Server {

    private static final int PORT = 1099;
    private static Registry registry;
    private static ArrayList<User> curUserList;
    private static ArrayList<Admin> curAdminList;
    private static final int MAXUSERNUM = 5;
//    private static AdminList adminList;

    public Server() {
    }

    public static void main(String args[]) {

        try {
            Server.curUserList = new ArrayList<User>();
            Server.curAdminList = new ArrayList<Admin>();

            UserAPI[] userStub = new UserAPI[MAXUSERNUM];
            AdminAPI[] adminStub = new AdminAPI[MAXUSERNUM];
            for (int i = 0; i < MAXUSERNUM; i++) {
                curUserList.add(new User());
                userStub[i] = (UserAPI) UnicastRemoteObject.exportObject(curUserList.get(i), 0);
                curAdminList.add(new Admin());
                adminStub[i] = (AdminAPI) UnicastRemoteObject.exportObject(curAdminList.get(i), 0);
            }

            // Bind the remote object's stub in the registry
            registry = LocateRegistry.createRegistry(PORT);
            for (int i = 0; i < MAXUSERNUM; i++) {

                registry.bind("UserAPI" + i, userStub[i]);
                registry.bind("AdminAPI" + i, adminStub[i]);
            }
//            UserList.loadUserData();
            if (StockList.getStockPool().isEmpty()) {
                StockList.loadStockPoolFromDisk(); //true or false
            }
            System.err.println("UserAPI Server ready");

            while (true) {
                StockList.updateWholeStockList();
                Thread.sleep(2000);
                System.out.println(StockList.getStockPool());
                StockList.saveStockPoolToDisk();
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
