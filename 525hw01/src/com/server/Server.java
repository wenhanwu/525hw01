package com.server;

import com.api.AdminAPI;
import com.api.UserAPI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * This class runs on the server side.
 *
 * @author jingboyu
 */
public class Server {

    private static final int PORT = 1099;
    private static Registry registry;
    private static ArrayList<User> curUserList;
    private static ArrayList<Admin> curAdminList;
    private static final int MAXUSERNUM = 5;

    public static void main(String args[]) {

        try {
            Server.curUserList = new ArrayList<User>();
            Server.curAdminList = new ArrayList<Admin>();
            
            // Create a pool of userStabs waiting for client.
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
            if (StockList.getStockPool().isEmpty()) {
                StockList.loadStockPoolFromDisk(); //true or false
            }
            if (UserList.getUserList().isEmpty()) {
                UserList.loadUserData();
            }
            System.err.println("UserAPI Server ready");

            while (true) {
                StockList.updateWholeStockList();                
                try {
                    // wait for 2 min
                    Thread.sleep(120000);
                } catch (InterruptedException ie) {
                    break;
                }
                StockList.saveStockPoolToDisk();
                UserList.syncUserList(curUserList);
            }
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
    }
}
