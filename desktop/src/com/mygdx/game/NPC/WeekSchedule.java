package com.mygdx.game.NPC;


import com.mygdx.game.Clock.Day;

import java.util.HashMap;
import java.util.Objects;

public class WeekSchedule {

    private final HashMap<Day, DaySchedule> daySchedules;

    public WeekSchedule(Builder builder){
        this.daySchedules = builder.daySchedules;
    }

    public HashMap<Day, DaySchedule> getDaySchedules(){
        return this.daySchedules;
    }


    public DaySchedule getDaySchedule(Day day){
        Objects.requireNonNull(day, "Cannot find the day schedule of a null day");
        DaySchedule daySchedule = daySchedules.get(day);
        Objects.requireNonNull(daySchedule, "No day schedule was found for the given day");
        return daySchedule;
    }

    public DaySchedule next(Day day){
        Objects.requireNonNull(day, "The day must not be null to fetch the next day's schedule");
        DaySchedule daySchedule = daySchedules.get(day.next());
        Objects.requireNonNull(daySchedule, "No day schedule was found for the next day");
        return daySchedule;
    }



    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private HashMap<Day, DaySchedule> daySchedules = null;

        public Builder daySchedules(HashMap<Day, DaySchedule> daySchedules){
            this.daySchedules = daySchedules;
            return this;
        }

        public WeekSchedule build(){
            Objects.requireNonNull(daySchedules, "The map of a week schedule must not be null");
            for(Day day : daySchedules.keySet()){
                Objects.requireNonNull(day, "The day key of a day schedule in a week schedule must not be null");
                Objects.requireNonNull(daySchedules.get(day), "Day schedules in a week schedule must not be null");
            }
            return new WeekSchedule(this);
        }
    }

}
