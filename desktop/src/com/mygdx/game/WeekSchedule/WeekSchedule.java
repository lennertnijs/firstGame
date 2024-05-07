package com.mygdx.game.WeekSchedule;

import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Time;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class WeekSchedule implements IWeekSchedule{

    private final Map<Day, Schedule> schedules;

    public WeekSchedule(Map<Day, Schedule> schedules){
        Objects.requireNonNull(schedules, "Map is null.");
        if(schedules.containsKey(null) || schedules.containsValue(null)) {
            throw new NullPointerException("Map contains null key or value.");
        }
        this.schedules = new HashMap<>(schedules);
    }

    public Map<Day, Schedule> schedules(){
        return new HashMap<>(schedules);
    }

    public Schedule getDaySchedule(Day day){
        validateDay(day);
        return schedules.get(day);
    }

    public boolean hasActivity(Day day, Time time){
        validateDay(day);
        Objects.requireNonNull(time, "Time is null.");
        return schedules.get(day).hasActivityAt(time);
    }

    public Activity getActivity(Day day, Time time){
        validateDay(day);
        Objects.requireNonNull(time, "Time is null.");
        return schedules.get(day).getActivityAt(time);
    }

    private void validateDay(Day day){
        Objects.requireNonNull(day, "Day is null.");
        if(!schedules.containsKey(day))
            throw new NoSuchElementException("No Schedule found for this Day.");
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