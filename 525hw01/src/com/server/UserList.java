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

    private static ArrayList<User> Ulist = new ArrayList();

    public static User fetchByUserID(int ID) {
        for (int i = 0; i < Ulist.size(); i++) {
            if ((Ulist.get(i)).getUserID() == ID) {
                return Ulist.get(i);
            }
        }
        return null;
    }
    
}
