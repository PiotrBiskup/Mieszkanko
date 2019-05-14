package com.example.mieszkanko.AccountSettings;

import com.google.android.gms.auth.api.accounttransfer.AccountTransfer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountSettings {

    static private String userID;
    static private String userNick;
    static private String flatID;
    static private List<String> productsNameToBuyList = new ArrayList<>();
    static private List<String> productsDescriptionToBuyList = new ArrayList<>();

    static private List<String> historyProductsName = new ArrayList<>();
    static private List<Double> historyProductsPrice = new ArrayList<>();
    static private List<Date> historyProductsDate = new ArrayList<>();
    static private List<String> historyProductsBuyer = new ArrayList<>();


    public static void addToToBuyList(String productName, String Description) {
        AccountSettings.productsNameToBuyList.add(productName);
        AccountSettings.productsDescriptionToBuyList.add(Description);
    }

    //getters and setters

    public static List<String> getProductsNameToBuyList() {
        return productsNameToBuyList;
    }

    public static void setProductsNameToBuyList(List<String> productsNameToBuyList) {
        AccountSettings.productsNameToBuyList = productsNameToBuyList;
    }

    public static List<String> getProductsDescriptionToBuyList() {
        return productsDescriptionToBuyList;
    }

    public static void setProductsDescriptionToBuyList(List<String> productsDescriptionToBuyList) {
        AccountSettings.productsDescriptionToBuyList = productsDescriptionToBuyList;
    }

    public static List<String> getHistoryProductsName() {
        return historyProductsName;
    }

    public static void setHistoryProductsName(List<String> historyProductsName) {
        AccountSettings.historyProductsName = historyProductsName;
    }

    public static List<Double> getHistoryProductsPrice() {
        return historyProductsPrice;
    }

    public static void setHistoryProductsPrice(List<Double> historyProductsPrice) {
        AccountSettings.historyProductsPrice = historyProductsPrice;
    }

    public static List<Date> getHistoryProductsDate() {
        return historyProductsDate;
    }

    public static void setHistoryProductsDate(List<Date> historyProductsDate) {
        AccountSettings.historyProductsDate = historyProductsDate;
    }

    public static List<String> getHistoryProductsBuyer() {
        return historyProductsBuyer;
    }

    public static void setHistoryProductsBuyer(List<String> historyProductsBuyer) {
        AccountSettings.historyProductsBuyer = historyProductsBuyer;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        AccountSettings.userID = userID;
    }

    public static String getUserNick() {
        return userNick;
    }

    public static void setUserNick(String userNick) {
        AccountSettings.userNick = userNick;
    }

    public static String getFlatID() {
        return flatID;
    }

    public static void setFlatID(String flatID) {
        AccountSettings.flatID = flatID;
    }
}
