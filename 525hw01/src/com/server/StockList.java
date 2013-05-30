/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class contains all the stock in a list, and supplies methods to
 * manipulate the list.
 *
 * @author haijun
 */
public class StockList {

    private static final int INITIAL_SHARES = 1000;
    private static ArrayList<StockExchange> stockPool = new ArrayList<StockExchange>();

    /**
     * Default constructor.
     */
    public StockList() {
        //not used yet.
    }

    /**
     * Return the StockPool (list).
     *
     * @return
     */
    public static ArrayList<StockExchange> getStockPool() {
        return stockPool;
    }

    /**
     * Return a StockExchange object by the ticker_name.
     *
     * @param ticker_name
     * @return
     */
    public static StockExchange getStockbyName(String ticker_name) {
        if (stockPool.isEmpty()) {
            if (addNewStock(ticker_name))
                return stockPool.get(0);
            else 
                return null;
        }
        for (int i = 0; i < stockPool.size(); ++i) {
            if (stockPool.get(i).isSameStock(ticker_name)) {
                return stockPool.get(i);
            }
        }
        if (addNewStock(ticker_name))
            return stockPool.get(stockPool.size()-1);
        else
            return null;
    }

    /**
     * Add a new Stock to the StockList, this method is invoked only when the
     * ticker_name is first queried.
     *
     * @param ticker_name
     * @return true if correctly added.
     */
    public static boolean addNewStock(String ticker_name) {
        Stock newStock = getStockInfoFromYahooFinancial(ticker_name);
        if (newStock != null && newStock.getPrice() != -1) {
            StockExchange st = new StockExchange(StockList.INITIAL_SHARES, newStock);
//            stockPool.add(new StockExchange(StockList.INITIAL_SHARES, newStock));
            stockPool.add(st);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Implement the method update() defined in AdminAPI.java.
     *
     * @param ticker_name
     * @param new_price
     */
    public static boolean update(String ticker_name, double new_price) {
        if (stockPool.isEmpty()) {
            return false;
        }
        for (int i = 0; i < stockPool.size(); ++i) {
            if (stockPool.get(i).isSameStock(ticker_name)) {
                stockPool.get(i).setPrice(new_price);
                return true;
            }
        }
        return false;
    }

    /**
     * This method loops through the StockList and update (only) the price of
     * the stocks.
     */
    public static void updateWholeStockList() {
        if (stockPool.isEmpty()) {
            return;
        }
        for (int i = 0; i < stockPool.size(); ++i) {
            stockPool.get(i).overwriteStockPrice(getStockInfoFromYahooFinancial(stockPool.get(i).getTickerName()));
        }
    }

    /**
     * This method retrieves Stock information the Yahoo financial service.
     *
     * @param ticker_name
     * @return an object of Stock, contains ticker_name and price.
     */
    public static Stock getStockInfoFromYahooFinancial(String ticker_name) {
        double stockPrice = -1;
        try {
            URL yahooFinance = new URL("http://finance.yahoo.com/d/quotes.csv?s=" + ticker_name + "&f=g");
            URLConnection yc = yahooFinance.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            if (!(inputLine = in.readLine()).equals("N/A")) {
                stockPrice = Double.parseDouble(inputLine);
            }
            in.close();
            return new Stock(ticker_name, stockPrice);
        } catch (IOException ex) {
            System.out.println("Could not get the stock information from the server.");
            return null;
        }
    }

    /**
     * This method saves all the information in the stockPool to the disk for
     * persistent storage.
     *
     * @return true or false
     */
    public static boolean saveStockPoolToDisk() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document savedStockList = docBuilder.newDocument();
            Element rootElement = savedStockList.createElement("stockList");
            savedStockList.appendChild(rootElement);


            for (int i = 0; i < stockPool.size(); ++i) {
                // StockExchange elements
                Element stockExchange = savedStockList.createElement("stockExchange");
                rootElement.appendChild(stockExchange);

                // ticker_name elements
                Element ticker_name = savedStockList.createElement("ticker_name");
                ticker_name.appendChild(savedStockList.createTextNode(stockPool.get(i).getTickerName()));
                stockExchange.appendChild(ticker_name);

                // price elements
                Element price = savedStockList.createElement("price");
                price.appendChild(savedStockList.createTextNode(Double.toString(stockPool.get(i).getPrice())));
                stockExchange.appendChild(price);

                // share elements
                Element share = savedStockList.createElement("share");
                share.appendChild(savedStockList.createTextNode(Integer.toString(stockPool.get(i).getShare())));
                stockExchange.appendChild(share);
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(savedStockList);
            StreamResult result = new StreamResult(new File("src/com/data/savedStockList.xml"));

            transformer.transform(source, result);

//            System.out.println("File saved!");
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
     * This method load the stocklist data from the disk and save into the
     * stockPool.
     *
     * @return true or false
     */
    public static boolean loadStockPoolFromDisk() {
        DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
        try {
            stockPool.clear();
            DocumentBuilder dombuilder = domfac.newDocumentBuilder();
            InputStream is = new FileInputStream("src/com/data/savedStockList.xml");
            Document doc = dombuilder.parse(is);
            Element root = doc.getDocumentElement();
            NodeList stockList = root.getChildNodes();
            if (stockList != null) {
                for (int i = 0; i < stockList.getLength(); i++) {
                    Node stockExchange = stockList.item(i);
                    if (stockExchange.getNodeType() == Node.ELEMENT_NODE) {
                        int checker = 0;
                        String ticker_name = "";
                        String price = "";
                        String share = "";
                        for (Node node = stockExchange.getFirstChild(); node != null; node = node.getNextSibling()) {
                            if (node.getNodeType() == Node.ELEMENT_NODE && checker < 3) {
                                if (node.getNodeName().equals("ticker_name")) {
                                    ticker_name = node.getFirstChild().getNodeValue();
                                    ++checker;
                                }
                                if (node.getNodeName().equals("price")) {
                                    price = node.getFirstChild().getNodeValue();
                                    ++checker;
                                }
                                if (node.getNodeName().equals("share")) {
                                    share = node.getFirstChild().getNodeValue();
                                    ++checker;
                                }
                            }
                            if (checker == 3) {
                                stockPool.add(new StockExchange(Integer.parseInt(share), ticker_name, Double.parseDouble(price)));
                                checker = 0;
//                                ticker_name = "";
//                                price = "";
//                                share = "";
                            }
                        }
                    }
                }
            }
            return true;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
