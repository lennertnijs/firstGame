package com.mygdx.game.V2;

public final class Time {

    private final int minutes;

    private Time(int minutes){
        this.minutes = minutes;
    }

    public static Time create(int minutes){
        Validator.withinRange(minutes, 0, Settings.MAX_TIME);
        return new Time(minutes);
    }

    public static Time createWithHours(int hours, int minutes){
        int timeInMinutes = hours * Settings.MINUTES_PER_HOUR + minutes;
        Validator.withinRange(timeInMinutes, 0, Settings.MAX_TIME);
        return new Time(timeInMinutes);
    }

    public int getTimeInMinutes(){
        return minutes;
    }

    public boolean isBefore(Time time){
        Validator.notNull(time);
        return minutes < time.minutes;
    }

    public boolean isAfter(Time time){
        Validator.notNull(time);
        return minutes > time.minutes;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Time)){
            return false;
        }
        Time t = (Time) other;
        return minutes == t.getTimeInMinutes();
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
