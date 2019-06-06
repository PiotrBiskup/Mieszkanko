
package com.example.mieszkanko.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Schedule {


    private List<Period> periodList = null;

    public Schedule(List<Period> periodList) {
        this.periodList = periodList;
    }

    public Schedule() {
    }

    public List<Period> getPeriodList() {
        return periodList;
    }

    public void setPeriodList(List<Period> periodList) {
        this.periodList = periodList;
    }

    public static Period choosePersonToClean(Apartment apartment, Schedule schedule){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 7);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Period period = new Period();
        int numberOfRooms=apartment.getRooms().size();
        List<Roomsschedule> roomsSchedules = new ArrayList<>();
        if( schedule.getPeriodList().size()<1) {
            for (int i = 0; i < numberOfRooms; i++) {
                if(apartment.getUsers().size()<=i){
                    Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(),false,apartment.getUsers().get(i-apartment.getUsers().size()));
                    roomsSchedules.add(roomsSchedule);
                }else {
                    Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(), false, apartment.getUsers().get(i));
                    roomsSchedules.add(roomsSchedule);
                }

            }
            period.setRoomsschedule(roomsSchedules);
            period.setTimestamp(c.getTimeInMillis());
            return period;

        }else if(schedule.getPeriodList().size()< apartment.getUsers().size()){
            for (int i = 0; i < numberOfRooms; i++) {
                if(i+schedule.getPeriodList().size()<apartment.getUsers().size()){
                    Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(),false,apartment.getUsers().get(i+schedule.getPeriodList().size()));
                    roomsSchedules.add(roomsSchedule);
                }else {
                    System.out.println(i + schedule.getPeriodList().size() - apartment.getUsers().size());
                    Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(), false, apartment.getUsers().get(i + schedule.getPeriodList().size() -apartment.getUsers().size()));
                    roomsSchedules.add(roomsSchedule);
                }
            }
            period.setRoomsschedule(roomsSchedules);
            period.setTimestamp(c.getTimeInMillis());
            return period;
        }else{
            for (Integer i = 0; i < numberOfRooms; i++) {
                Roomsschedule roomsSchedule = new Roomsschedule(apartment.getRooms().get(i).getRoom(),false,whoWasLast(schedule,apartment.getUsers().size(),i));
                roomsSchedules.add(roomsSchedule);
            }
            period.setRoomsschedule(roomsSchedules);
            period.setTimestamp(c.getTimeInMillis());
            return period;
        }

    }

    public static  String whoWasLast(Schedule schedule, Integer numberOfLocators, Integer roomNumber){
        System.out.println(schedule.getPeriodList().size() -numberOfLocators +"    "+ roomNumber);
        List<Roomsschedule> list= schedule.getPeriodList().get(schedule.getPeriodList().size() -numberOfLocators ).getRoomsschedule();
        String user = list.get(roomNumber).getUser();
        return user;
    }
}
