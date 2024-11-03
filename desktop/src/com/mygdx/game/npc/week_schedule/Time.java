package com.mygdx.game.npc.week_schedule;

import java.util.Objects;

public final class Time implements Comparable<Time>{

    public static final int HOURS_PER_DAY = 24;
    public static final int MINUTES_PER_HOUR = 60;
    public static final int MINUTES_PER_DAY = HOURS_PER_DAY * MINUTES_PER_HOUR;
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

    public Time(int minutes){
        if(minutes < 0 || minutes >= MINUTES_PER_DAY){
            throw new IllegalArgumentException("Minutes is negative or bigger than MINUTES_PER_DAY.");
        }
        this.minutes = minutes;
    }

    public int hours(){
        return minutes / MINUTES_PER_HOUR;
    }

    public int minutes(){
        return minutes % MINUTES_PER_HOUR;
    }

    public int asMinutes(){
        return minutes;
    }

    public Time increase(int increase){
        if(increase <= 0){
            throw new IllegalArgumentException("Increase is negative or 0.");
        }
        int minutesWithinRange = (minutes + increase) % MINUTES_PER_DAY;
        return new Time(minutesWithinRange);
    }

    @Override
    public int compareTo(Time time) {
        Objects.requireNonNull(time, "Time is null.");
        return Integer.compare(minutes, time.minutes);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Time time))
            return false;
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
