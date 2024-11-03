package com.mygdx.game.npc.week_schedule;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class WeekSchedule{

    private final Map<Day, Schedule> schedules;

    public WeekSchedule(Map<Day, Schedule> schedules){
        Objects.requireNonNull(schedules, "Map is null.");
        if(schedules.containsKey(null) || schedules.containsValue(null)) {
            throw new NullPointerException("Map contains null key or value.");
        }
        this.schedules = new HashMap<>(schedules);
    }

    public Activity getActivity(Day day, Time time){
        Objects.requireNonNull(day, "Day is null.");
        if(!schedules.containsKey(day))
            throw new NoSuchElementException("No Schedule found for this Day.");
        Objects.requireNonNull(time, "Time is null.");
        return schedules.get(day).getActivityAt(time);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof WeekSchedule))
            return false;
        WeekSchedule schedule = (WeekSchedule) other;
        return schedules.equals(schedule.schedules);
    }

    @Override
    public int hashCode(){
        return schedules.hashCode();
    }

    @Override
    public String toString(){
        return String.format("WeekSchedule[Schedules=%s]", schedules);
    }
}