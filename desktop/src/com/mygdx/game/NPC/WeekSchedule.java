package com.mygdx.game.NPC;


import com.mygdx.game.Clock.Day;

import java.util.ArrayList;
import java.util.Objects;

public class WeekSchedule {

    private final ArrayList<DaySchedule> daySchedules;

    public WeekSchedule(Builder builder){
        this.daySchedules = builder.daySchedules;
    }

    public ArrayList<DaySchedule> getDaySchedules(){
        return this.daySchedules;
    }

    public DaySchedule next(Day currentDay){
        Objects.requireNonNull(currentDay, "Cannot move to the next day schedule with null");
        Day nextDay = currentDay.next();
        return daySchedules.stream().filter(e -> e.getDay() == nextDay).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private ArrayList<DaySchedule> daySchedules = new ArrayList<>();

        public Builder daySchedules(ArrayList<DaySchedule> daySchedules){
            this.daySchedules = daySchedules;
            return this;
        }

        public Builder addDaySchedule(DaySchedule daySchedule){
            this.daySchedules.add(daySchedule);
            return this;
        }

        public WeekSchedule build(){
            Objects.requireNonNull(daySchedules, "The list of day schedules of a week schedule must not be null");
            for(DaySchedule daySchedule : daySchedules){
                Objects.requireNonNull(daySchedule, "No day schedule of a week schedule can be null");
            }
            return new WeekSchedule(this);
        }
    }

}
