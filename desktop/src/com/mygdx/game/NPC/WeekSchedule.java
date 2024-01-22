package com.mygdx.game.NPC;

import com.mygdx.game.Clock.DayName;

import java.util.HashMap;

public class WeekSchedule {

    private final HashMap<DayName, DaySchedule> daySchedules = new HashMap<>();

    public WeekSchedule(){
    }

    public HashMap<DayName, DaySchedule> getDaySchedules(){
        return this.daySchedules;
    }

    public void addDaySchedule(DaySchedule daySchedule){
        if(daySchedule == null){
            throw new IllegalArgumentException("Cannot store a null day schedule in the week schedule.");
        }
        daySchedules.put(daySchedule.getDay(), daySchedule);
    }
}
