
package com.example.mieszkanko.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("apartments")
    @Expose
    private List<String> apartments = null;
    @SerializedName("nick")
    @Expose
    private String nick;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
