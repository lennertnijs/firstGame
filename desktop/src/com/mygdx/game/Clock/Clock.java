package com.mygdx.game.Clock;

import com.mygdx.game.NPC.Day;

import static com.mygdx.game.Constants.*;

public class Clock {
    int minutes;
    Day day;

    /**
     * Constructor for a {@code Clock}.
     * The {@code Clock} is a 24-hour round clock.
     * The lowest value is 00:00 (midnight) and the highest value is 23:59.
     * @param minutes The minutes at which the {@code Clock} should initiate.
     *                To convert a HH:MM to minutes, use 60*HH + MM.
     */
    public Clock(int minutes){
        if(minutes < MIN_TIME_MINUTES || minutes > MAX_TIME_MINUTES){
            throw new IllegalArgumentException("Cannot create clock with invalid time.");
        }
        this.minutes = minutes;
    }

    /**
     * @return The {@code Clock}'s time in minutes.
     */
    public int getTime(){
        return this.minutes;
    }

    /**
     * @return The {@code Clock}'s current time as a {@code String} in HH:MM format.
     */
    public String getTimeInHHMM(){
        return minutes/60 + ":" + minutes%60;
    }

    /**
     * Increases the time with the given number.
     * @param extraMinutes The time to be added to the clock.
     *                     Cannot be negative.
     *                     Cannot exceed MAX_TIME_MINUTES + 1. An increase of MAX_TIME_MINUTES + 1 will move time ahead
     *                     exactly 24 hours.
     */
    public void increaseTime(int extraMinutes){
        if(extraMinutes < MIN_TIME_MINUTES || extraMinutes > MAX_TIME_MINUTES + 1){
            throw new IllegalArgumentException("Invalid time to add");
        }
        int newTime = minutes + extraMinutes;
        if(newTime > MAX_TIME_MINUTES){
            this.minutes = newTime - (MAX_TIME_MINUTES + 1);
            return;
        }
        this.minutes = newTime;
    }

}
