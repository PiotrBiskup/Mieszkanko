
package com.example.mieszkanko.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Apartment {

    @SerializedName("apart")
    @Expose
    private String apart;
    @SerializedName("interval")
    @Expose
    private Integer interval;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rooms")
    @Expose
    private List<Room> rooms = null;

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
