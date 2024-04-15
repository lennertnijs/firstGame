package com.mygdx.game.V2.Util;

import java.util.Objects;

public final class Time {

    public static int HOURS_PER_DAY = 24;
    public static int MINUTES_PER_HOUR = 60;
    private final int hours;
    private final int minutes;

    private Time(int hours, int minutes){
        this.hours = hours;
        this.minutes = minutes;
    }

    public static Time create(int hours, int minutes){
        if(hours < 0 || hours >= HOURS_PER_DAY)
            throw new IllegalArgumentException("Cannot create a Time object because the hours value is invalid.");
        if(minutes < 0 || minutes >= MINUTES_PER_HOUR)
            throw new IllegalArgumentException("Cannot create a Time object because the minutes value is invalid.");
        return new Time(hours, minutes);
    }

    public int getHours(){
        return hours;
    }

    public int getMinutes(){
        return minutes;
    }

    public boolean before(Time time){
        Objects.requireNonNull(time, "Cannot check whether a null Time is before this Time.");
        return hours < time.hours || (hours == time.hours && minutes < time.minutes);
    }

    public boolean after(Time time){
        Objects.requireNonNull(time, "Cannot check whether a null Time is after this Time.");
        return hours > time.hours || (hours == time.hours && minutes > time.minutes);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Time)){
            return false;
        }
        Time t = (Time) other;
        return hours == t.hours && minutes == t.minutes;
    }

    @Override
    public int hashCode(){
        return minutes;
    }

    @Override
    public String toString(){
        return String.format("Time[hours=%d, minutes=%d]", hours, minutes);
    }
}
