
package com.example.mieszkanko.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Purchased {

    @SerializedName("item")
    @Expose
    private String item;
    @SerializedName("buyer")
    @Expose
    private String buyer;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Double price;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
