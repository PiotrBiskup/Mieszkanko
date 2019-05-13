
package com.example.mieszkanko.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule {

    @SerializedName("apart")
    @Expose
    private String apart;
    @SerializedName("roomsschedule")
    @Expose
    private List<Roomsschedule> roomsschedule = null;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;

    public String getApart() {
        return apart;
    }

    public void setApart(String apart) {
        this.apart = apart;
    }

    public List<Roomsschedule> getRoomsschedule() {
        return roomsschedule;
    }

    public void setRoomsschedule(List<Roomsschedule> roomsschedule) {
        this.roomsschedule = roomsschedule;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }



}
