/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api;
import com.server.Stock;
import java.lang.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author jingboyu
 */
public interface ServerAPI extends Remote {
    
	public boolean isConnect() throws RemoteException;
    	//public boolean isAdmin(String user_name) throws RemoteException; // return true when the user is the admin, reture false otherwise.
	//public boolean isValidUser(String user_name) throws RemoteException; // return true when the user is the admin, reture false otherwise.
        //public int addNewUser(String user_name) throws RemoteException;   //return user type, 1 means ordinary user, 2 means admin
    
}
