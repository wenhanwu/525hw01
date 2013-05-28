/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import java.util.ArrayList;

/**
 *
 * @author w
 */
public class UserList {

    private static ArrayList<User> uList = new ArrayList();

    public static User fetchByUserID(int ID) {
        for (int i = 0; i < uList.size(); i++) {
            if ((uList.get(i)).getUserID() == ID) {
                return uList.get(i);
            }
        }
        return null;
    }
    
}
