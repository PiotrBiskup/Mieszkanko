
package com.example.mieszkanko.Models;


import java.util.Date;

public class Purchased {


    private String buyer;
    private String name;
    private Double price;
    private String purchaseDate;

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Purchased(String buyer, String name, Double price, String date) {
        this.buyer = buyer;
        this.name = name;
        this.price = price;
        this.purchaseDate = date;
    }

    public Purchased() {
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
