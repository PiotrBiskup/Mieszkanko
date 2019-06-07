package com.example.mieszkanko.AccountSettings;

import com.example.mieszkanko.Models.Apartment;
import com.example.mieszkanko.Models.Schedule;
import com.example.mieszkanko.Models.ShoppingList;
import com.example.mieszkanko.Models.User;
import com.google.android.gms.auth.api.accounttransfer.AccountTransfer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AccountSettings {

    private static ShoppingList shoppingList = new ShoppingList();
    private static Apartment apartment = new Apartment();
    private static User user = new User();
    private static Schedule schedule = new Schedule();
    public static ShoppingList getShoppingList() {
        return shoppingList;
    }
    public static CountDownLatch countdown = new CountDownLatch(1);
    public static Schedule getSchedule() {
        return schedule;
    }

    public static void setSchedule(Schedule schedule) {
        AccountSettings.schedule = schedule;
    }

    public static void setShoppingList(ShoppingList shoppingList) {
        AccountSettings.shoppingList = shoppingList;
    }

    public static Apartment getApartment() {
        return apartment;
    }

    public static void setApartment(Apartment apartment) {
        AccountSettings.apartment = apartment;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        AccountSettings.user = user;
    }

}
