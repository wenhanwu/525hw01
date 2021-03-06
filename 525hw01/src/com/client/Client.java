package com.client;

import com.api.AdminAPI;
import com.api.UserAPI;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entrance of the client side.
 * @author jingboyu
 */
public class Client {

    private static String userName = "";
    private static final int MAXUSERNUM = 5;

    /**
     * Helper function: specify user type.
     * @return 
     */
    public static int welcome() {       //start

        int type = 0; // return user type, 1 means ordinary user, 2 means admin, -1 means quit

        Scanner scan = new Scanner(System.in);

        System.out.println("Please input your username (q to quit) :");
        System.out.println("(Usage: [USER name] or [ADMIN name])");
        do {
            String userInput = scan.nextLine();
            userInput = userInput.toLowerCase();
            if (userInput.equalsIgnoreCase("q")) {  //user selection: quit 
                return -1;
            }

            // get user type or add new user
            try {
                //handles user input
                if (userInput.startsWith("user ", 0)) {
                    type = 1;
                    userName = userInput.substring(5).trim().toLowerCase(); 
                    if (userName.equals("")) {
                        System.out.println("Please input your username. Username cannot be null.");
                        return 0;
                    }
                    break;
                } else if (userInput.startsWith("admin ", 0)) {
                    type = 2;
                    userName = userInput.substring(5).trim().toLowerCase();
                    if (userName.equals("")) {
                        System.out.println("Please input your username. Username cannot be null.");
                        return 0;
                    }
                    break;
                } else {
                    System.out.println("Invalid user type!");
                    return 0;
                }
            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        } while (true);
        return type;
    }

    /**
     * Helper function.
     */
    static void userPrompt() {
        System.out.println("---------------------------------------------------------");
        System.out.println("|Commmands:	s - Sell stock           b - Buy stock\t|");
        System.out.println("|		l - My stock list        c - Check price|");
        System.out.println("|           \tq - Quit\t\t\t\t|");
        System.out.println("---------------------------------------------------------");
        System.out.print("Please select your operation: ");
    }

    /**
     * Helper function.
     */
    static void adminPrompt() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("|commands:	l - Get Stock List    u - Update Stock Price |");
        System.out.println("|		q - Quit                                     |");
        System.out.println("--------------------------------------------------------------");
        System.out.print("Please select your operation: ");

    }

    /**
     * User operations on client side: buy, sell, list, check and quit.
     * @param user user object
     * @return 1
     */
    public static int tradeForUser(UserAPI user) {			

        Scanner scan = new Scanner(System.in);

        do {
            try {
                System.out.println();
                System.out.format("User name:  %s\t\t    Your balance: %.2f\n", userName, user.getUserBalance());
            } catch (RemoteException ex) {
                System.out.println(ex.toString());
                System.out.println("Cannot connect to server! Abort!");
                break;
            }
            userPrompt();

            String userInput = scan.nextLine();
            if (userInput.equalsIgnoreCase("q")) {  //user selection: quit
                try {
                    //user selection: quit
                    user.saveUserListToDisk();
                    user.freeCurrentUser();
                } catch (RemoteException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }

            if (userInput.equalsIgnoreCase("s")) // user selection: sell
            {

                try {

                    System.out.print("please input ticker name: ");
                    String ticker_name = scan.nextLine().trim().toUpperCase();

                    double price = user.getMarketPrice(ticker_name);

                    //validation on price information
                    if (price == -1.0) {
                        System.out.println("This ticker_name does not exist!");
                        continue;
                    } else {
                        int shares = user.getNumShare(ticker_name);
                        if (shares <= 0) {
                            System.out.println("You do not have shares of " + ticker_name + ".");
                            continue;
                        }
                        System.out.println("You have " + shares + " shares of " + ticker_name + ", current price is " + price + ".");

                    }

                    System.out.print("please input number of shares to sell: ");

                    //validation
                    int num_stock = 0;
                    if (scan.hasNextInt()) {
                        num_stock = scan.nextInt();
                        scan.nextLine();
                        if (num_stock <= 0) {
                            System.out.println(num_stock + " must be positive integer!");
                            continue;
                        }
                    } else {
                        System.out.println(scan.nextLine() + " is an invalid input!");
                        continue;
                    }

                    int errorCode = user.sell(ticker_name, num_stock);

                    //output user operation result: fail, success
                    if (errorCode == 0) {
                        System.out.println("Sold successfully!");
                        continue;
                    } else if (errorCode == 1) {
                        int shares = user.getNumShare(ticker_name);

                        System.out.println("You do not have enough shares to sell!");
                        System.out.println("Your have " + shares + " shares of " + ticker_name + ".");
                        continue;

                    }

                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                }

            } else if (userInput.equalsIgnoreCase("b")) {   //user selection: buy
                try {
                    System.out.print("please input ticker name: ");
                    String ticker_name = scan.nextLine().trim().toUpperCase();

                    double price = user.getMarketPrice(ticker_name);
                    
                    //validation
                    if (price == -1) {
                        System.out.println("This ticker_name does not exist!");
                        continue;
                    } else {
                        System.out.println("the current price of " + ticker_name + " is " + price + ".");
                    }

                    System.out.print("please input number of shares to buy: ");

                    //validation on user input
                    int num_stock = 0;
                    if (scan.hasNextInt()) {
                        num_stock = scan.nextInt();
                        scan.nextLine();
                        if (num_stock <= 0) {
                            System.out.println(num_stock + " must be positive integer!");
                            continue;
                        }
                    } else {
                        System.out.println(scan.nextLine() + " is an invalid input!");
                        continue;
                    }

                    int errorCode = user.buy(ticker_name, num_stock); 

                    //output user operation result
                    if (errorCode == 0) {
                        System.out.println("Transaction done!");
                    } else if (errorCode == 0) {
                        System.out.println("Ticker name does not exist!");
                    } else if (errorCode == 1) {
                        int shares = user.getAvailableShares(ticker_name);
                        if (shares == -1) {
                            System.out.println("Can not get inforamtion of shares!");
                        } else {
                            System.out.println("There is not enough shares to buy!");
                            System.out.println("The current number of shares of " + ticker_name + " available to buy is " + shares + ".");
                        }
                    } else if (errorCode == 2) {
                        double balance = user.getUserBalance();
                        if (balance == -1) {
                            System.out.println("Can not find user balance information!");
                        } else {
                            System.out.println("There is no enough balance!");
                            System.out.format("Your current balance is %.2f\n", balance);
                        }
                    } else {
                        System.out.println("Never been here!");
                    }


                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                }

            } else if (userInput.equalsIgnoreCase("l")) { //user selection: list my stocks
                try {
                    System.out.println(user.displayStocksHold());


                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                }

            } else if (userInput.equalsIgnoreCase("c")) {   //user selection: check

                try {
                    System.out.print("please input ticker name: ");
                    String ticker_name = scan.nextLine().trim().toUpperCase();

                    double price = user.getMarketPrice(ticker_name);
                    //validation
                    if (price == -1) {
                        System.out.println("The ticker_name is not found!");
                    } else {
                        System.out.println("The current price of " + ticker_name + " is " + price + ".");
                    }
                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
            } else {}
        } while (true);
        return 1;
    }

    public static int tradeForAdmin(AdminAPI admin) {			//start

        Scanner scan = new Scanner(System.in);
        do {
            adminPrompt();
            String userInput = scan.nextLine();
            if (userInput.equalsIgnoreCase("q")) {  //admin selection: quit
                break;
            }
            if (userInput.equalsIgnoreCase("u")) // admin selection: update
            {
                try {
                    //user input need validation
                    System.out.print("please input ticker name: ");
                    String ticker_name = scan.nextLine();
                    System.out.print("please input new price: ");

                    //validation on user input
                    double new_price = 0;
                    if (scan.hasNextDouble()) {
                        new_price = scan.nextDouble();
                        scan.nextLine();
                        if (new_price <= 0) {
                            System.out.println(new_price + " must be positive!");
                            continue;
                        }
                    } else {
                        System.out.println(scan.nextLine() + " is an invalid input!");
                        continue;
                    }

                    boolean errorCode = admin.update(ticker_name, new_price);
                    if (errorCode == true) {
                        System.out.println("Ticker is successfully updated");
                    } else if (errorCode == false) {
                        System.out.println("Ticker update failed!");
                    } else {
                        System.out.println("Never been here!");
                    }


                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }

            } else if (userInput.equalsIgnoreCase("l")) {   //admin selection: list stock list
                try {
                    System.out.println(admin.displayMarketStocks());


                } catch (Exception e) {
                    System.err.println("Client exception: " + e.toString());
                    e.printStackTrace();
                }
            } else {}
            
        } while (true);
        return 1;
    }

    /**
     * Main function.
     * @param args 
     */
    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];

        try {
            Registry registry = LocateRegistry.getRegistry(host);
            System.out.println("Server connected!");
            int type = welcome(); // get user name, and return user type, 1 ordinary user, 2 admin
            if (type == -1) {
                return;
            }
            while (type == 0) {
                type = welcome();
            }

            if (type == 1) {
                UserAPI userCheck = null;
                for (int i = 0; i < MAXUSERNUM; i++) {
                    userCheck = (UserAPI) registry.lookup("UserAPI" + i);
                    if (userCheck.getUserName() != null) {
                        if (userCheck.getUserName().equals(userName)) {
                            System.out.println("You are already activated, please change a user!");
                            System.exit(0);
                        }
                    }
                }

                boolean findFlag = false;
                UserAPI user = null;
                int i = 0;
                while (!findFlag && i < MAXUSERNUM) {
                    user = (UserAPI) registry.lookup("UserAPI" + i);
                    if (user.getUserName() == null) {
                        findFlag = true;
                        break;
                    }
                    i++;
                }
                if (!findFlag) {

                    System.out.println("No room for you!");

                    System.out.println("Not room for you!");

                    System.exit(0);
                }

                user.populateCurrentUser(userName);
                tradeForUser(user);// call user operations
            } else if (type == 2) {
                boolean findFlag = false;
                AdminAPI admin = null;
                int i = 0;
                while (!findFlag && i < MAXUSERNUM) {
                    admin = (AdminAPI) registry.lookup("AdminAPI" + i);
                    if (admin.getAdminName() == null) {
                        findFlag = true;
                        break;
                    }
                    i++;
                }
                if (!findFlag) {

                    System.out.println("No room for you!");

                    System.out.println("Not room for you!");

                    System.exit(0);
                }
                admin.startAdmin(userName);
                tradeForAdmin(admin); // call admin operations
            } else {
                System.out.println("Never been here!");
            }
 
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }

    }
}
