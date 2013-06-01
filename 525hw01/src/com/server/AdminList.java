/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author wenhanwu
 */
public class AdminList {
        private static ArrayList<Admin> adminList = new ArrayList();

    /**
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
