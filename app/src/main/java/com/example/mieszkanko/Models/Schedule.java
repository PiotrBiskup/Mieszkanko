
package com.example.mieszkanko.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Schedule {


    private List<List<Roomsschedule>> roomsschedule = null;
    private long timestamp;
    public Schedule() {
    }

    public Schedule(List<List<Roomsschedule>> roomsschedule, Integer timestamp) {
        this.roomsschedule = roomsschedule;
        this.timestamp = timestamp;
    }



    public List<List<Roomsschedule>> getRoomsschedule() {
        return roomsschedule;
    }

    public void setRoomsschedule(List<List<Roomsschedule>> roomsschedule) {
        this.roomsschedule = roomsschedule;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    public static void choosePersonToClean(Apartment apartment,Schedule schedule){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 7);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        int numberOfRooms=apartment.getRooms().size();
        List<Roomsschedule> roomsSchedules = new ArrayList<>();
        if( schedule.getRoomsschedule().size()<1) {
            for (int i = 0; i < numberOfRooms; i++) {
                if(apartment.getUsers().size()<=i){
                    Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(),false,apartment.getUsers().get(i-apartment.getUsers().size()).getNick());
                    roomsSchedules.add(roomsSchedule);
                }else {
                    Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(), false, apartment.getUsers().get(i).getNick());
                    roomsSchedules.add(roomsSchedule);
                }

            }
            schedule.getRoomsschedule().add(roomsSchedules);
            schedule.setTimestamp(c.getTimeInMillis());
        }else if(schedule.getRoomsschedule().size()< apartment.getUsers().size()){
            for (int i = 0; i < numberOfRooms; i++) {
                if(i+schedule.getRoomsschedule().size()<apartment.getUsers().size()){
                    Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(),false,apartment.getUsers().get(i+schedule.getRoomsschedule().size()).getNick());
                    roomsSchedules.add(roomsSchedule);
                }else {
                    System.out.println(i + schedule.getRoomsschedule().size() - apartment.getUsers().size());
                    Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(), false, apartment.getUsers().get(i + schedule.getRoomsschedule().size() -apartment.getUsers().size()).getNick());
                    roomsSchedules.add(roomsSchedule);
                }
            }
            schedule.getRoomsschedule().add(roomsSchedules);
            schedule.setTimestamp(c.getTimeInMillis());
        }else{
            for (Integer i = 0; i < numberOfRooms; i++) {
                Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(),false,whoWasLast(schedule,apartment.getUsers().size(),i));
                roomsSchedules.add(roomsSchedule);
            }
            schedule.getRoomsschedule().add(roomsSchedules);
            schedule.setTimestamp(c.getTimeInMillis());
        }

    }

    public static  String whoWasLast(Schedule schedule, Integer numberOfLocators, Integer roomNumber){
        System.out.println(schedule.getRoomsschedule().size() -numberOfLocators +"    "+ roomNumber);
        List<Roomsschedule> list= schedule.getRoomsschedule().get(schedule.getRoomsschedule().size() -numberOfLocators );
        String user = list.get(roomNumber).getUser();
        return user;
    }
}
