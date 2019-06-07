package com.example.mieszkanko.Models;


public class Room {
//    private String room;
    private String description;
    private String name;


    public Room(String description, String name) {
        this.description = description;
        this.name = name;



    }

    public Room() {}

//    public String getRoom() {
//        return room;
//    }
//
//    public void setRoom(String room) {
//        this.room = room;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}