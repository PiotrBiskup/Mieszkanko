package com.example.mieszkanko.Models;

import java.util.List;

public class Period {
    private List<Roomsschedule> roomsschedule = null;
    private long timestamp;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Period() {
    }

    public Period(List<Roomsschedule> roomsschedule, long timestamp) {
        this.roomsschedule = roomsschedule;
        this.timestamp = timestamp;
    }

    public List<Roomsschedule> getRoomsschedule() {
        return roomsschedule;
    }

    public void setRoomsschedule(List<Roomsschedule> roomsschedule) {
        this.roomsschedule = roomsschedule;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
