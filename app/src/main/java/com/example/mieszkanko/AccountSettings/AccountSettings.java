package com.example.mieszkanko.AccountSettings;

import com.example.mieszkanko.Models.Apartment;
import com.example.mieszkanko.Models.ShoppingList;
import com.example.mieszkanko.Models.User;
import com.google.android.gms.auth.api.accounttransfer.AccountTransfer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountSettings {

    private static ShoppingList shoppingList = new ShoppingList();

    public static ShoppingList getShoppingList() {
        return shoppingList;
    }

    public static void setShoppingList(ShoppingList shoppingList) {
        AccountSettings.shoppingList = shoppingList;
    }
}
