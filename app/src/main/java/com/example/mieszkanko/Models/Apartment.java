
package com.example.mieszkanko.Models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Apartment {


//    private String apart;
//    private Integer interval;
    private String name;
    private List<Room> rooms = null;
    private List<String> roommates = new ArrayList<>();
    private List<User> roommatesUser = new ArrayList<User>();

    public List<User> getRoommatesUser() {
        return roommatesUser;
    }

    public void setRoomatesUser(ArrayList<User> roommatesUser) {
        this.roommatesUser = roommatesUser;
    }

    public void addRoomatesUser(User roommateUser) {
        this.roommatesUser.add(roommateUser);
    }

    public Apartment(String name, List<Room> rooms, List<String> roommates) {
        this.name = name;
        this.rooms = rooms;
        this.roommates = roommates;
    }

    public Apartment() {}

    public List<String> getRoommates() {
        return roommates;
    }

    public void setRoommates(ArrayList<String> roommates) {
        this.roommates = roommates;
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
