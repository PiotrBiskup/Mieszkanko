
package com.example.mieszkanko.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Apartment {

    private String apart;
    private Integer interval;
    private String name;
    private List<Room> rooms = null;
    private List<User>users;
    public Apartment() {
    }

    public Apartment(String apart, Integer interval, String name, List<Room> rooms) {
        this.apart = apart;
        this.interval = interval;
        this.name = name;
        this.rooms = rooms;
    }

    public Apartment(String apart, Integer interval, String name, List<Room> rooms, List<User> users) {
        this.apart = apart;
        this.interval = interval;
        this.name = name;
        this.rooms = rooms;
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getApart() {
        return apart;
    }

    public void setApart(String apart) {
        this.apart = apart;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }


}
