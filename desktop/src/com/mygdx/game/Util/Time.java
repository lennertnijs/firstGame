package com.mygdx.game.Util;

import java.util.Objects;

public final class Time implements Comparable<Time>{

    public static final int HOURS_PER_DAY = 24;
    public static final int MINUTES_PER_HOUR = 60;
    private final int minutes;

    public Time(int hours, int minutes){
        if(hours < 0 || hours >= HOURS_PER_DAY) {
            throw new IllegalArgumentException("Hours is negative or bigger than HOURS_PER_DAY.");
        }
        if(minutes < 0 || minutes >= MINUTES_PER_HOUR) {
            throw new IllegalArgumentException("Minutes is negative or bigger than MINUTES_PER_HOUR.");
        }
        this.minutes = hours * MINUTES_PER_HOUR + minutes;
    }

    public int hours(){
        return minutes / MINUTES_PER_HOUR;
    }

    public int minutes(){
        return minutes % MINUTES_PER_HOUR;
    }

    @Override
    public int compareTo(Time time) {
        Objects.requireNonNull(time, "Time is null.");
        return Integer.compare(minutes, time.minutes);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Time))
            return false;
        Time time = (Time) other;
        return minutes == time.minutes;
    }

    @Override
    public int hashCode(){
        return minutes;
    }

    @Override
    public String toString() {
        return String.format("Time[hours=%d, minutes=%d]", hours(), minutes());
    }
}
