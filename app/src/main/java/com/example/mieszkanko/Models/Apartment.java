
package com.example.mieszkanko.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Apartment {
//  private String apart;
//    private Integer interval;
    private String name;
    private List<Room> rooms = null;
    private List<String>users;
    public Apartment() {
    }

    public Apartment( String name, List<Room> rooms) {

        this.name = name;
        this.rooms = rooms;
    }

    public Apartment( String name, List<Room> rooms, List<String> users) {
        this.name = name;
        this.rooms = rooms;
        this.users = users;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
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
