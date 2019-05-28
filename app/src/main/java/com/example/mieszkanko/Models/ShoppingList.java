
package com.example.mieszkanko.Models;

import java.util.ArrayList;
import java.util.List;



public class ShoppingList {

    private List<Purchased> purchased;
    private List<Product> toBuy;

    public ShoppingList() {
        this.purchased = new ArrayList<>();
        this.toBuy = new ArrayList<>();
    }

//    public void addToToBuy(String productName, String description){
//        Product product = new Product(productName, description);
//        this.toBuy.add(product);
//    }

    public List<Purchased> getPurchased() {
        return purchased;
    }

    public void setPurchased(List<Purchased> purchased) {
        this.purchased = purchased;
    }

    public List<Product> getToBuy() {
        return toBuy;
    }

    public void setToBuy(List<Product> toBuy) {
        this.toBuy = toBuy;
    }




}
