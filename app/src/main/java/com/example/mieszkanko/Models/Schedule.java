
package com.example.mieszkanko.Models;

import java.util.ArrayList;
import java.util.List;

public class Schedule {


    private String apart;
    private List<List<Roomsschedule>> roomsschedule = null;
    private Integer timestamp;
    public Schedule() {
    }

    public Schedule(String apart, List<List<Roomsschedule>> roomsschedule, Integer timestamp) {
        this.apart = apart;
        this.roomsschedule = roomsschedule;
        this.timestamp = timestamp;
    }

    public Schedule(String apart, List<List<Roomsschedule>> roomsschedule) {
        this.apart = apart;
        this.roomsschedule = roomsschedule;
    }

    public List<List<Roomsschedule>> getRoomsschedule() {
        return roomsschedule;
    }

    public void setRoomsschedule(List<List<Roomsschedule>> roomsschedule) {
        this.roomsschedule = roomsschedule;
    }

    public String getApart() {
        return apart;
    }

    public void setApart(String apart) {
        this.apart = apart;
    }



    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }


    public static void choosePersonToClean(Apartment apartment,Schedule schedule){
        int numberOfRooms=apartment.getRooms().size();
        List<Roomsschedule> roomsSchedules = new ArrayList<>();
        if( schedule.getRoomsschedule().size()<1) {
            for (int i = 0; i < numberOfRooms; i++) {
                if(apartment.getUsers().size()<=i){
                    Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(),false,apartment.getUsers().get(i-apartment.getUsers().size()).getNick(),123);
                    roomsSchedules.add(roomsSchedule);
                }else {
                    Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(), false, apartment.getUsers().get(i).getNick(), 123);
                    roomsSchedules.add(roomsSchedule);
                }

            }
            schedule.getRoomsschedule().add(roomsSchedules);
        }else if(schedule.getRoomsschedule().size()< apartment.getUsers().size()){
            for (int i = 0; i < numberOfRooms; i++) {
                if(i+schedule.getRoomsschedule().size()<apartment.getUsers().size()){
                    Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(),false,apartment.getUsers().get(i+schedule.getRoomsschedule().size()).getNick(),123);
                    roomsSchedules.add(roomsSchedule);
                }else {
                    System.out.println(i + schedule.getRoomsschedule().size() - apartment.getUsers().size());
                    Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(), false, apartment.getUsers().get(i + schedule.getRoomsschedule().size() -apartment.getUsers().size()).getNick(),123);
                    roomsSchedules.add(roomsSchedule);
                }
            }
            schedule.getRoomsschedule().add(roomsSchedules);
        }else{
            for (Integer i = 0; i < numberOfRooms; i++) {
                Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(),false,whoWasLast(schedule,apartment.getUsers().size(),i),123 );
                roomsSchedules.add(roomsSchedule);
            }
            schedule.getRoomsschedule().add(roomsSchedules);
        }

    }

    public static  String whoWasLast(Schedule schedule, Integer numberOfLocators, Integer roomNumber){
        System.out.println(schedule.getRoomsschedule().size() -numberOfLocators +"    "+ roomNumber);
        List<Roomsschedule> list= schedule.getRoomsschedule().get(schedule.getRoomsschedule().size() -numberOfLocators );
        String user = list.get(roomNumber).getUser();
        return user;
    }
}
