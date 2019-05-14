
package com.example.mieszkanko.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class User {

    private List<String> apartments = null;
    private String nick;

    public User(List<String> apartments, String nick) {
        this.apartments = apartments;
        this.nick = nick;
    }

    public List<String> getApartments() {
        return apartments;
    }

    public void setApartments(List<String> apartments) {
        this.apartments = apartments;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }




}
