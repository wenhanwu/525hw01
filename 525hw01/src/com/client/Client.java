/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.lang.*;

/**
 *
 * @author jingboyu
 */
public class Client {

    public static int welcome(String userName) {			//start

        int type = 0; // return user type, 0 means user name not exist, 1 means ordinary user, 2 means admin

        Scanner scan = new Scanner(System.in);

        System.out.println("Please input your username (q to quit) :");
        do {
            String userInput = scan.nextLine();
            if (userInput == "q") {
                break;
            }

            // get user type or add new user
            try {
                Registry registry = LocateRegistry.getRegistry(host);
                Client stub = (Client) registry.lookup("ServerAPI");
                if (stub.isValidUser(userInput)) {
                    userName = userInput;
                    if (stub.isAdmin(userInput)) {
                        type = 2;
                    } else {
                        type = 1;
                    }
                    break;
                } else {
                    type = stub.addNewUser(userInput);
                }
            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }



        } while (true);


        return type;
    }

    static void userPrompt() {

        System.out.println("----------------------------------------------------------");
        System.out.println("|Commmands:	s - Sell stocks         b - Buy stocks       |");
        System.out.println("|		l - Get My Stock List   q - Quit             |");
        System.out.println("----------------------------------------------------------");

    }

    static void adminPrompt() {

        System.out.println("----------------------------------------------------------");
        System.out.println("|commands:	l - Get Stock List    u - Update Stock Price |");
        System.out.println("|		q - Quit                                     |");
        System.out.println("----------------------------------------------------------");

    }

    public static int tradeForUser(String userName) {			//start

        Scanner scan = new Scanner(System.in);
        
        userPrompt();
        System.out.println("Please input your operation:");
        
        do {
            String userInput = scan.nextLine();
            if (userInput == "q") {
                break;
            }

            if (userInput == "s") // to do. equals() inconsistent type fix for later debug
            {

                try {
                    Registry registry = LocateRegistry.getRegistry(host);
                    Client stub = (Client) registry.lookup("UserAPI");
                    // to do 
                    // query UserAPI and sell stock


                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
            } else if (userInput == "b") {
                try {
                    Registry registry = LocateRegistry.getRegistry(host);
                    Client stub = (Client) registry.lookup("UserAPI");
                    // to do 
                    // query UserAPI and buy stock


                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
            } else if (userInput == "l") {
                try {
                    Registry registry = LocateRegistry.getRegistry(host);
                    Client stub = (Client) registry.lookup("UserAPI");
                    // to do 
                    // query UserAPI and list stock


                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
            } else {
                System.out.println("Please input your operation:");
            }


        } while (true);


        return trade_type;        
    }

    public static int tradeForAdmin(String userName) {			//start

        Scanner scan = new Scanner(System.in);
        
        adminPrompt();
        System.out.println("Please input your operation:");
        
        do {
            String userInput = scan.nextLine();
            if (userInput == "q") {
                break;
            }

            if (userInput == "u") // to do. equals() inconsistent type fix for later debug
            {

                try {
                    Registry registry = LocateRegistry.getRegistry(host);
                    Client stub = (Client) registry.lookup("AdminAPI");
                    // to do 
                    // query AdminAPI and sell stock


                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
            } else if (userInput == "l") {
                try {
                    Registry registry = LocateRegistry.getRegistry(host);
                    Client stub = (Client) registry.lookup("AdminAPI");
                    // to do 
                    // query AdminAPI and list stock


                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
            } else {
                System.out.println("Please input your operation:");
            }


        } while (true);


        return trade_type; 
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];

        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Client stub = (Client) registry.lookup("ServerAPI");
            if (stub.isConnect()) // query ServerAPI for connection
            {
                System.out.println("connected!");
                String userName = "";
                int type = welcome(userName); // get user name, and return user type, 0 no such user, 1 ordinary user, 2 admin
                if (type == 1) {
                    tradeForUser(userName);// something to do with user
                } else if (type == 2) {
                    tradeForAdmin(userName); // something to do with admin
                } else {
                    System.out.println("wrong user type!");
                }
            }  catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
