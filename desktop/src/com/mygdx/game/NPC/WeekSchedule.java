package com.mygdx.game.NPC;

import java.util.HashMap;

public class WeekSchedule {

    private final HashMap<Day, DaySchedule> daySchedules = new HashMap<>();

    public WeekSchedule(){
    }

    public HashMap<Day, DaySchedule> getDaySchedules(){
        return this.daySchedules;
    }

    public void addDaySchedule(DaySchedule daySchedule){
        if(daySchedule == null){
            throw new IllegalArgumentException("Cannot store a null day schedule in the week schedule.");
        }
        daySchedules.put(daySchedule.getDay(), daySchedule);
    }
}
