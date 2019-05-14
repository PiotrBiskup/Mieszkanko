
package com.example.mieszkanko.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Mieszkanko {

    @SerializedName("apartments")
    @Expose
    private List<Apartment> apartments = null;
    @SerializedName("schedule")
    @Expose
    private List<Schedule> schedule = null;
    @SerializedName("shopping list")
    @Expose
    private List<ShoppingList> shoppingList = null;
    @SerializedName("users")
    @Expose
    private List<User> users = null;

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public List<ShoppingList> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<ShoppingList> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }



}
