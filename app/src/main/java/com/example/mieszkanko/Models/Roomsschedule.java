
package com.example.mieszkanko.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Roomsschedule {

    @SerializedName("room")
    @Expose
    private String room;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("user")
    @Expose
    private String user;

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
