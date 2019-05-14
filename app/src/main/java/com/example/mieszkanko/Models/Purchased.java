
package com.example.mieszkanko.Models;


public class Purchased {


    private String buyer;
    private String name;
    private Double price;

    public Purchased(String buyer, String name, Double price) {
        this.buyer = buyer;
        this.name = name;
        this.price = price;
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
