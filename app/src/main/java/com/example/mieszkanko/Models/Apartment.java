
package com.example.mieszkanko.Models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Apartment {

    private String apart;
    private Integer interval;
    private String name;
    private List<Room> rooms = null;
    private ArrayList<String> flatmates = null;

    public ArrayList<String> getFlatmates() {
        return flatmates;
    }

    public void setFlatmates(ArrayList<String> flatmates) {
        this.flatmates = flatmates;
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