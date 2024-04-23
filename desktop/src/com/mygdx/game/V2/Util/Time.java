package com.mygdx.game.V2.Util;

import java.util.Objects;

/**
 * Represents a moment in time, so hours & minutes.
 * IMMUTABLE
 */
public final class Time {

    /**
     * The amount of hours per day.
     */
    public static final int HOURS_PER_DAY = 24;
    /**
     * The amount of minutes per hour.
     */
    public static final int MINUTES_PER_HOUR = 60;
    /**
     * The time in minutes.
     */
    private final int minutes;

    /**
     * Creates a new immutable {@link Time}.
     * @param hours The hours. 0 <= hours < HOURS_PER_DAY
     * @param minutes The minutes. 0 <= minutes < MINUTES_PER_HOUR
     *
     * @throws IllegalArgumentException If the hours or minutes is invalid.
     */
    public Time(int hours, int minutes){
        if(hours < 0 || hours >= HOURS_PER_DAY)
            throw new IllegalArgumentException("Hours is negative or bigger than HOURS_PER_DAY.");
        if(minutes < 0 || minutes >= MINUTES_PER_HOUR)
            throw new IllegalArgumentException("Minutes is negative or bigger than MINUTES_PER_HOUR.");
        this.minutes = hours * MINUTES_PER_HOUR + minutes;
    }

    /**
     * @return The hours.
     */
    public int hours(){
        return minutes / MINUTES_PER_HOUR;
    }

    /**
     * @return The minutes.
     */
    public int minutes(){
        return minutes % MINUTES_PER_HOUR;
    }

    /**
     * Checks whether this {@link Time} is strictly before the given {@link Time}.
     * @param time The second {@link Time}. Cannot be null.
     *
     * @return True if strictly before. False otherwise.
     */
    public boolean before(Time time){
        Objects.requireNonNull(time, "Time is null.");
        return minutes < time.minutes;
    }

    /**
     * Checks whether this {@link Time} is strictly after the given {@link Time}.
     * @param time The second {@link Time}. Cannot be null.
     *
     * @return True if strictly after. False otherwise.
     */
    public boolean after(Time time){
        Objects.requireNonNull(time, "Time is null.");
        return minutes > time.minutes;
    }

    /**
     * Compares this {@link Time} to the given object and returns true if they're equal. False otherwise.
     * Two {@link Time}s are equal if they have the same hours & minutes.
     *
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Time))
            return false;
        Time t = (Time) other;
        return minutes == t.minutes;
    }

    /**
     * @return The hash code of this {@link Time}.
     */
    @Override
    public int hashCode(){
        return minutes;
    }

    /**
     * @return The string representation of this {@link Time}.
     */
    @Override
    public String toString(){
        return String.format("Time[hours=%d, minutes=%d]", hours(), minutes());
    }
}
