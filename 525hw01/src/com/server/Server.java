/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import com.api.UserAPI;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author jingboyu
 */
public class Server 
{
        public Server() {}

        public static void main(String args[]) {

            try {
                User obj = new User("Jane Doe", 1100, new ArrayList<StockExchange>());
                UserAPI stub = (UserAPI) UnicastRemoteObject.exportObject(obj, 0);

                // Bind the remote object's stub in the registry
                Registry registry = LocateRegistry.getRegistry();
                registry.bind("UserAPI", stub);

                System.err.println("UserAPI Server ready");
            } catch (Exception e) {
                System.err.println("Server exception: " + e.toString());
                e.printStackTrace();
            }
        }  
}
