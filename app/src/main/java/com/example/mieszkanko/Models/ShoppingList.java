
package com.example.mieszkanko.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ShoppingList {

    @SerializedName("apart")
    @Expose
    private String apart;
    @SerializedName("purchased")
    @Expose
    private List<Purchased> purchased = null;
    @SerializedName("to_buy")
    @Expose
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
