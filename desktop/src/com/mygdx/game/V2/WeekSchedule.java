package com.mygdx.game.V2;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class WeekSchedule {

    private final Map<Day, DaySchedule> daySchedules;

    private WeekSchedule(Map<Day, DaySchedule> daySchedules){
        this.daySchedules = daySchedules;
    }

    public static WeekSchedule create(Map<Day, DaySchedule> daySchedules){
        Objects.requireNonNull(daySchedules, "Cannot create a WeekSchedule with null.");
        if(daySchedules.containsKey(null) || daySchedules.containsValue(null))
            throw new NullPointerException("Cannot create a WeekSchedule with a null Day or DaySchedule.");
        return new WeekSchedule(daySchedules);
    }

    public Map<Day, DaySchedule> getDaySchedulesWithDays(){
        return daySchedules;
    }

    public DaySchedule getDaySchedule(Day day){
        Objects.requireNonNull(day, "Cannot fetch a DaySchedule for null.");
        if(!daySchedules.containsKey(day))
            throw new NoSuchElementException("No DaySchedule exists for this Day.");
        return daySchedules.get(day);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof WeekSchedule)){
            return false;
        }
        WeekSchedule schedule = (WeekSchedule) other;
        return daySchedules.equals(schedule.daySchedules);
    }

    @Override
    public int hashCode(){
        return daySchedules.hashCode();
    }

    @Override
    public String toString(){
        return String.format("WeekSchedule[DaySchedules=%s]", daySchedules);
    }
}