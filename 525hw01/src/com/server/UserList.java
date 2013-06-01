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
 * This is to maintain a List to store and manage all of the Users. The data
 * should be write into the xml file
 *
 * @author wenhanwu
 */
public class UserList {
    //The static Array list to maintain the user data

    private static ArrayList<User> uList = new ArrayList<User>();

    /**
     * To return the Array list Obj
     *
     * @return
     */
    public static ArrayList<User> getUserList() {
        return uList;
    }

    /**
     *
     * @param userName
     * @return The User OBJ
     * @return null Cannot find the User in the User List
     */
    public static User fetchByUserName(String userName) {
        if (uList.isEmpty()) {
            loadUserData();
        }
        if (!uList.isEmpty()) {
            for (int i = 0; i < uList.size(); i++) {
                if (uList.get(i).getUserName().equals(userName)) {
                    return uList.get(i);
                }
            }
        }
        uList.add(new User(userName));
        return uList.get(uList.size() - 1);
    }

    /**
     * Load the user data from the xml file userData.xml.
     *
     * @return
     */
    public static boolean loadUserData() {
        DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dombuilder = domfac.newDocumentBuilder();
            InputStream is = new FileInputStream("src/com/data/userData.xml");
            Document doc = dombuilder.parse(is);
            Element root = doc.getDocumentElement();
            NodeList userList = root.getChildNodes();
            if (userList != null) {
                for (int i = 0; i < userList.getLength(); i++) {
                    Node userUnit = userList.item(i);
                    if (userUnit.getNodeType() == Node.ELEMENT_NODE) {
                        int checker = 0;
                        String userName = "";
                        String balance = "";
                        for (Node node = userUnit.getFirstChild(); node != null; node = node.getNextSibling()) {
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                ArrayList<StockExchange> sEList = new ArrayList<StockExchange>();
                                if (node.getNodeName().equals("userName")) {
                                    userName = node.getFirstChild().getNodeValue();
                                    checker++;
                                }
                                if (node.getNodeName().equals("balance")) {
                                    balance = node.getFirstChild().getNodeValue();
                                    checker++;
                                }
                                if (node.getNodeName().equals("stockList")) {
                                    NodeList stockList = node.getChildNodes();

                                    if (stockList != null) {
                                        for (int j = 0; j < stockList.getLength(); j++) {
                                            Node stockExchange = stockList.item(j);
                                            if (stockExchange.getNodeType() == Node.ELEMENT_NODE) {
                                                int innerChecker = 0;
                                                String ticker_name = "";
                                                String price = "";
                                                String share = "";
                                                for (Node stockNode = stockExchange.getFirstChild(); stockNode != null; stockNode = stockNode.getNextSibling()) {
                                                    if (stockNode.getNodeType() == Node.ELEMENT_NODE) {
                                                        if (stockNode.getNodeName().equals("ticker_name")) {
                                                            ticker_name = stockNode.getFirstChild().getNodeValue();
                                                            innerChecker++;
                                                        }
                                                        if (stockNode.getNodeName().equals("price")) {
                                                            price = stockNode.getFirstChild().getNodeValue();
                                                            innerChecker++;
                                                        }
                                                        if (stockNode.getNodeName().equals("share")) {
                                                            share = stockNode.getFirstChild().getNodeValue();
                                                            innerChecker++;
                                                        }
                                                        if (innerChecker == 3) {
                                                            sEList.add(new StockExchange(Integer.parseInt(share), ticker_name, Double.parseDouble(price)));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    checker++;
                                }
                                if (checker == 3) {
                                    uList.add(new User(userName, Double.parseDouble(balance), sEList));
                                }
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Write the user data to the xml file userData.xml.
     *
     * @return true or false
     */
    public static boolean saveUserData() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document userList = docBuilder.newDocument();
            Element rootElement = userList.createElement("userList");
            userList.appendChild(rootElement);
//Start to write data to file
            for (int i = 0; i < uList.size(); i++) {
                Element user = userList.createElement("user");
                rootElement.appendChild(user);

                Element userName = userList.createElement("userName");
                userName.appendChild(userList.createTextNode(uList.get(i).getUserName()));
                user.appendChild(userName);

                Element balance = userList.createElement("balance");
                balance.appendChild(userList.createTextNode(Double.toString(uList.get(i).getBalance())));
                user.appendChild(balance);

                Element userStockList = userList.createElement("stockList");
                user.appendChild(userStockList);
//For the stock list of the user, write to file
                for (int j = 0; j < uList.get(i).getStockListofUser().size(); j++) {
                    Element stockExchange = userList.createElement("stockExchange");
                    userStockList.appendChild(stockExchange);
                    Element ticker_name = userList.createElement("ticker_name");
                    ticker_name.appendChild(userList.createTextNode(uList.get(i).getStockListofUser().get(j).getTickerName()));
                    stockExchange.appendChild(ticker_name);
                    Element price = userList.createElement("price");
                    price.appendChild(userList.createTextNode(Double.toString(uList.get(i).getStockListofUser().get(j).getPrice())));
                    stockExchange.appendChild(price);
                    Element share = userList.createElement("share");
                    share.appendChild(userList.createTextNode(Integer.toString(uList.get(i).getStockListofUser().get(j).getShare())));
                    stockExchange.appendChild(share);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(userList);
            StreamResult result = new StreamResult(new File("src/com/data/userData.xml"));
            transformer.transform(source, result);
            return true;

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            return false;
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
            return false;
        }
    }

    /**
     * Sychronize the currentActiveUserList to the UserList maintained by the
     * system.
     *
     * @param currentActiveUserList
     */
    public static void syncUserList(ArrayList<User> currentActiveUserList) {
        int count = uList.size();
        for (int i = 0; i < currentActiveUserList.size(); ++i) {
            for (int j = 0; j < count; ++j) {
                if (currentActiveUserList.get(i).getUserName() != null && currentActiveUserList.get(i).getUserName().equals(uList.get(j).getUserName())) {
                    uList.get(j).setBalance(currentActiveUserList.get(i).getBalance());
                }
            }
        }
    }
}
