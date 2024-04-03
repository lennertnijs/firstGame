package com.mygdx.game.V2;

public final class Time {

    private final int hours;
    private final int minutes;

    private Time(int hours, int minutes){
        this.hours = hours;
        this.minutes = minutes;
    }

    public static Time create(int hours, int minutes){
        Validator.withinRange(hours, 0, Settings.HOURS_PER_DAY);
        Validator.withinRange(minutes, 0, Settings.MINUTES_PER_HOUR);
        return new Time(hours, minutes);
    }

    public int getMinutes(){
        return minutes;
    }

    public int getHours(){
        return hours;
    }

    public boolean before(Time time){
        Validator.notNull(time);
        return minutes < time.minutes;
    }

    public boolean after(Time time){
        Validator.notNull(time);
        return minutes > time.minutes;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Time)){
            return false;
        }
        Time t = (Time) other;
        return minutes == t.minutes;
    }

    @Override
    public int hashCode(){
        return minutes;
    }

    @Override
    public String toString(){
        return String.format("Time[minutes = %d]", minutes);
    }
}
