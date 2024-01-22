package com.mygdx.game.Clock;

import com.mygdx.game.NPC.Day;

import static com.mygdx.game.Constants.*;

public class Clock {
    Day day;
    int minutes;


    /**
     * Constructor for a {@code Clock}.
     * The {@code Clock} is a 24-hour round clock that includes a {@code Day}.
     * The lowest value is 00:00 (midnight) and the highest value is 23:59.
     */
    public Clock(Builder builder){
        this.day = builder.day;
        this.minutes = builder.minutes;
    }

    public Day getDay(){
        return this.day;
    }

    public int getTimeInMinutes(){
        return this.minutes;
    }

    /**
     * @return The time in HH:MM format.
     */
    public String getTimeInHHMM(){
        int hrs = minutes/60;
        int mins = minutes%60;
        return toTwoCharacterString(hrs) + ":" + toTwoCharacterString(mins);
    }

    private String toTwoCharacterString(int number){
        return number < 10 ? "0" + number : Integer.toString(number);
    }

    /**
     * @param minutesToAdd Cannot be negative or MAX_TIME_MINUTES + 1.
     *                     An increase of MAX_TIME_MINUTES + 1 will move time ahead exactly 24 hours.
     */
    public void increaseTimeByMinutes(int minutesToAdd){
        TimeValidator.validateTimeToAddInMinutes(minutesToAdd);
        checkAndHandleDayChange(this.minutes + minutesToAdd);
    }

    private void checkAndHandleDayChange(int newMinutes){
        boolean dayChange = minutes > MAX_TIME_MINUTES;
        if(dayChange){
            this.day = day.next();
            this.minutes = newMinutes - (MAX_TIME_MINUTES + 1);
        }else{
            this.minutes = newMinutes;
        }
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Day day;
        private int minutes;

        public Builder(){
        }

        public Builder day(Day day){
            this.day = day;
            return this;
        }

        public Builder minutes(int minutes){
            this.minutes = minutes;
            return this;
        }

        public Clock build(){
            if(minutes < MIN_TIME_MINUTES || MAX_TIME_MINUTES < minutes){
                throw new IllegalArgumentException("Time is invalid");
            }
            return new Clock(this);
        }
    }
}
