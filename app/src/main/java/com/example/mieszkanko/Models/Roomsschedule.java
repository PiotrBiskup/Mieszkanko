
package com.example.mieszkanko.Models;



public class Roomsschedule {


    private String room;
    private Boolean status;
    private String user;

    public Roomsschedule() {
    }

    public Roomsschedule(String room, Boolean status, String user) {
        this.room = room;
        this.status = status;
        this.user = user;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }



}
