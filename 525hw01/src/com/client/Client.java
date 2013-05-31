/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import com.api.AdminAPI;
import com.api.ServerAPI;
import com.api.UserAPI;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.lang.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author jingboyu
 */
public class Client {

    public static int welcome(String userName) {			//start

        int type = 0; // return user type, 0 means user name not exist, 1 means ordinary user, 2 means admin

        Scanner scan = new Scanner(System.in);

        System.out.println("Please input your username (q to quit) :");
          System.out.println("(Usage: [USER name] or [ADMIN name])");
        do {
            String userInput = scan.nextLine();
            if (userInput.equalsIgnoreCase("q")) {
                break;
            }

            // get user type or add new user
            try {
                if(userInput.startsWith("USER", 4))
                {
                    type = 1;
                }
                else if(userInput.startsWith("ADMIN", 5))
                {
                    type = 2;
                }
                else
                {
                    System.out.println("Invalid user type!");
                    
                }
//                stub.returnUserType(type);
                
            }catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
                }
        
        
//                if (stub.isValidUser(userInput)) {
//                    userName = userInput;
//                    if (stub.isAdmin(userInput)) {
//                        type = 2;
//                    } else {
//                        type = 1;
//                    }
//                    break;
//                } else {
//                    type = stub.addNewUser(userInput);
//                }
//            } catch (Exception e) {
//                System.err.println("Client exception: " + e.toString());
//                e.printStackTrace();
//            }
//
//
//
        } while (true);
//

        return type;
    }

    static void userPrompt() {

        System.out.println("Please select your operation: ");
        System.out.println("----------------------------------------------------------");
        System.out.println("|Commmands:	s - Sell stocks         b - Buy stocks       |");
        System.out.println("|		l - Get My Stock List   q - Quit             |");
        System.out.println("----------------------------------------------------------");

    }

    static void adminPrompt() {

        System.out.println("Please select your operation: ");
        System.out.println("----------------------------------------------------------");
        System.out.println("|commands:	l - Get Stock List    u - Update Stock Price |");
        System.out.println("|		q - Quit                                     |");
        System.out.println("----------------------------------------------------------");

    }

    public static int tradeForUser(UserAPI user, String userName) {			//start

        Scanner scan = new Scanner(System.in);
        
        userPrompt();
        
        do {
            String userInput = scan.nextLine();
            if (userInput.equalsIgnoreCase("q")) {  //user selection: quit
                break;
            }
            
            if (userInput.equalsIgnoreCase("s")) // user selection: sell
            {

                try { 
                    
                    System.out.println("please input ticker name: ");                   
                    String ticker_name = scan.nextLine();
                    
                    double price = user.getMarketPrice(ticker_name);
                    
                    //validation on price information
                    if(price == -1)
                    {
                       System.out.println("Can not get price information!");  
                    }
                    else
                    {
                        System.out.println("the current price of " + ticker_name + " is " + price + "."); 
                    }
                    
                    System.out.println("please input number of shares to sell: ");     
                    
                    //validation
                    int num_stock = scan.nextInt();
                    
                    int errorCode= user.sell(ticker_name, num_stock);
 
                    //output user operation result: fail, success
                    if(errorCode == 0)
                    {
                        System.out.println("Ticker name does not exist!");
                    }
                    else if(errorCode == 1)
                    {
                        int shares = user.getNumShare(ticker_name);
                        if(shares == -1)
                        {
                            System.out.println("Can not get information of shares!");
                        }
                        else
                        {
                            System.out.println("the number of shares are not enough to sell!");
                            System.out.println("Your have " + shares + " shares of " + ticker_name + ".");
                        }
                        
                    }
//                    else if(errorCode == 2)
//                    {
//                        double balance = getUserBalance();
//                        System.out.println("There is no enough balance!");
//                        System.out.println("Your current balance is " + balance);
//                    }
                    else
                    {
                        System.out.println("Never been here!");
                    }


                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
                
            } else if (userInput.equalsIgnoreCase("b")) {   //user selection: buy
                try {
                    System.out.println("please input ticker name: ");                   
                    String ticker_name = scan.nextLine();
                                        
                    double price = user.getMarketPrice(ticker_name);
                    
                    //validation
                    if(price == -1)
                    {
                       System.out.println("Can not get price information!");  
                    }
                    else
                    {
                        System.out.println("the current price of " + ticker_name + " is " + price + "."); 
                    }
                    
                    System.out.println("please input number of shares to buy: "); 
                    
                    //validation on user input
                    int num_stock = scan.nextInt();
                    
                    int errorCode = user.buy(ticker_name, num_stock);
 
                    //output user operation result
                    if (errorCode == 0)
                    {
                        System.out.println("Transaction done!");
                    }
                    else if(errorCode == 0)
                    {
                        System.out.println("Ticker name does not exist!");
                    }
                    else if(errorCode == 1)
                    {
                        int shares = user.getAvailableShares(ticker_name);
                        if(shares == -1)
                        {
                            System.out.println("Can not get inforamtion of shares!");
                        }
                        else
                        {
                            System.out.println("There is no enough shares to buy!");
                            System.out.println("The current number of shares of " + ticker_name + " available to buy is " + shares + ".");
                        }
                    }
                    else if(errorCode == 2)
                    {
                        double balance = user.getUserBalance();
                        if(balance == -1)
                        {
                           System.out.println("Can not find user balance information!"); 
                        }
                        else
                        {
                            System.out.println("There is no enough balance!");
                            System.out.println("Your current balance is " + balance);
                        }
                    }
                    else
                    {
                        System.out.println("Never been here!");
                    }


                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
                
            } else if (userInput.equalsIgnoreCase("l")) { //user selection: list my stocks
                try {
                    user.displayStocksHold();


                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
                
            } else {
                
                System.out.println("Invalid input! Please select your operation:");
            }


        } while (true);


        return 1;        
    }

    public static int tradeForAdmin(AdminAPI admin, String userName) {			//start
        
        Scanner scan = new Scanner(System.in);
        
        adminPrompt();
        
        do {
            String userInput = scan.nextLine();
            if (userInput.equalsIgnoreCase("q")) {  //admin selection: quit
                break;
            }
            
            if (userInput.equalsIgnoreCase("u")) // admin selection: update
            {

                try {
                   
                    //user input need validation
                    System.out.println("please input ticker name: ");                   
                    String ticker_name = scan.nextLine();
                    System.out.println("please input new price: ");  
                    
                    //validation on user input
                    double new_price = scan.nextInt();
                    
                    boolean errorCode = admin.update(ticker_name, new_price);
                    if(errorCode == true)
                    {
                        System.out.println("Ticker is successfully updated");
                    }
                    else if(errorCode == false)
                    {
                        System.out.println("Ticker update failed!");
                    }
                    else
                    {
                        System.out.println("Never been here!");
                    }


                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
                
            } else if (userInput.equalsIgnoreCase("l")) {   //admin selection: list stock list
                try {
                    admin.displayMarketStocks();


                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
                
            } else {
                
                System.out.println("Invalid input! Please select your operation:");
            }


        } while (true);


        return 1;        
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];

        
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            ServerAPI stub = (ServerAPI) registry.lookup("ServerAPI");
            if (stub.isConnect()) // query ServerAPI for connection
            {
                System.out.println("Server connected!");
                String userName = "";
                int type = welcome(userName); // get user name, and return user type, 1 ordinary user, 2 admin
                if (type == 1) {
                     UserAPI user = (UserAPI) registry.lookup("UserAPI");
                    tradeForUser(user, userName);// call user operations
                } else if (type == 2) {
                    AdminAPI admin = (AdminAPI) registry.lookup("AdminAPI");
                    tradeForAdmin(admin, userName); // call admin operations
                } else {
                    System.out.println("Never been here!");
                }
            }  
        }
        catch (Exception e) 
        {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        
        
//        try {
//            Registry registry = LocateRegistry.getRegistry(host);
//            UserAPI user = (UserAPI) registry.lookup("UserAPI");
//            // sell buy and list stocks
//            
//            System.out.println(user.test());
//        }
//        catch (Exception e) 
//        {
//            System.err.println("Client exception: " + e.toString());
//            e.printStackTrace();
//        }
    }
}
