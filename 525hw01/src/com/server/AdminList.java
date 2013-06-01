/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import java.util.ArrayList;

/**
 *
 * @author wenhanwu
 */
public class AdminList {

    private static ArrayList<Admin> adminList = new ArrayList();

    /**
     * Get the admin Obj by the admin name
     *
     * @param adminName
     * @return The Admin OBJ
     * @return null Cannot find the Admin in the Admin List
     */
    public static Admin fetchByAdminName(String adminName) {
        for (int i = 0; i < adminList.size(); i++) {
            if ((adminList.get(i)).getAdminName().equals(adminName)) {
                return adminList.get(i);
            }
        }
        return null;
    }
}
