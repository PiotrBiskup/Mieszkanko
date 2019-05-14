
package com.example.mieszkanko.Models;

import java.util.List;

public class Schedule {


    private String apart;
    private List<Roomsschedule> roomsschedule = null;
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
