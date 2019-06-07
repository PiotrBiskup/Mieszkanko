
package com.example.mieszkanko.Models;

import java.util.List;



public class User {

    private String apartment ;
    private String nick;
    private String key;
    public User(String apartment, String nick) {
        this.apartment = apartment;
        this.nick = nick;
    }

    public User() {}

    public String getKey() {
        return key;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartments(String apartment) {
        this.apartment = apartment;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }



}
