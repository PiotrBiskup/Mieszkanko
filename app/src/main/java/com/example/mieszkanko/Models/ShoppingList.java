
package com.example.mieszkanko.Models;

import java.util.List;



public class ShoppingList {

    private String apart;
    private List<Purchased> purchased = null;
    private List<Product> toBuy = null;

    public String getApart() {
        return apart;
    }

    public void setApart(String apart) {
        this.apart = apart;
    }

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
