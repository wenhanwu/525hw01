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
   
		public static void welcome(){			//start

		Scanner scan = new Scanner(System.in);
		int input = -1;			//input is the index of words[];
		String userInput = "";

		initialPrompt();
		System.out.print("- ");		//prompt user to enter lines.

			do
			{
				String str = scan.nextLine();
				input = parseUserInput(str);		//parse user input: search the key word and return its index in words[].

			}
			while (true);
		}
		
        public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
		try {
				Registry registry = LocateRegistry.getRegistry(host);
				Client stub = (Client) registry.lookup("ServerAPI");
				if (stub.isConnect())
				{
					System.out.println("connected!");
					welcome();
				}
				else
				{
					System.out.println("disconnected!");
				}
			} catch (Exception e) {
				System.err.println("Client exception: " + e.toString());
				e.printStackTrace();
			}


		}
}
