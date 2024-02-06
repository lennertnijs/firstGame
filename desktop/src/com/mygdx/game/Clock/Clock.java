package com.mygdx.game.Clock;

import java.util.Objects;

import static com.mygdx.game.Constants.*;

public class Clock {

    private final Calendar calendar;
    private Season season;
    private Day day;
    private int timeInMinutes;
    private int dayOfTheSeason;
    private boolean active;

    public Clock(Builder builder){
        this.calendar = builder.calendar;
        this.season = builder.season;
        this.day = builder.day;
        this.timeInMinutes = builder.timeInMinutes;
        this.dayOfTheSeason = builder.dayOfTheSeason;
        this.active = true;
    }

    public Calendar getCalendar(){
        return this.calendar;
    }

    public Season getSeason(){
        return this.season;
    }

    public Day getDay(){
        return this.day;
    }

    public int getTimeInMinutes(){
        return this.timeInMinutes;
    }

    public int getDayOfTheSeason(){
        return this.dayOfTheSeason;
    }

    protected int getSeasonLength(){
        return calendar.getSeasonLength(season);
    }

    public boolean getActive(){
        return this.active;
    }

    protected void setActive(boolean active){
        this.active = active;
    }

    /**
     * @return The time in HH:MM format. 0's will be added to keep the size of each of the numbers consistent.
     */
    public String getTimeInHHMM(){
        int hours = timeInMinutes/MINUTES_PER_HOUR;
        int minutes = timeInMinutes%MINUTES_PER_HOUR;
        int hourDigits = numberOfDigitsInInt(HOURS_PER_DAY-1);
        int minuteDigits = numberOfDigitsInInt(MINUTES_PER_HOUR-1);
        String consistentHrs = addLeadingZerosTillAppropriateLength(hours, hourDigits);
        String consistentMins = addLeadingZerosTillAppropriateLength(minutes, minuteDigits);
        return consistentHrs + ":" + consistentMins;
    }

    private String addLeadingZerosTillAppropriateLength(int number, int appropriateLength){
        int digitsInNumber = numberOfDigitsInInt(number);
        if(appropriateLength < digitsInNumber){
            throw new IllegalArgumentException("Cannot convert a larger size number to a smaller size");
        }
        StringBuilder numberString = new StringBuilder(String.valueOf(number));
        while(numberString.length() < appropriateLength){
            numberString.insert(0, "0");
        }
        return numberString.toString();
    }

    private int numberOfDigitsInInt(int number){
        return String.valueOf(number).length();
    }


    protected void incrementTimeByOne(){
        timeInMinutes++;
        handleDayChange();
        handleSeasonChange();
    }
    /**
     * Increases the clock's time with the given minutes. Also takes day and season into consideration.
     */
    protected void increaseTimeByMinutes(int minutes){
        if(minutes < 0 ||  minutes > MINUTES_PER_DAY ){
            throw new IllegalArgumentException("Invalid amount of minutes to add");
        }
        timeInMinutes += minutes;
        handleDayChange();
        handleSeasonChange();
    }

    private void handleDayChange(){
        boolean dayChange = timeInMinutes >= MINUTES_PER_DAY;
        if(dayChange){
            day = day.next();
            timeInMinutes -= MINUTES_PER_DAY;
            dayOfTheSeason += 1;
        }
    }

    private void handleSeasonChange(){
        boolean seasonChange = dayOfTheSeason > calendar.getSeasonLength(season);
        while(seasonChange){
            season = season.next();
            dayOfTheSeason = 1;
            seasonChange = dayOfTheSeason > calendar.getSeasonLength(season);
        }
    }





    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Calendar calendar;
        private Season season;
        private Day day;
        private int timeInMinutes;
        private int dayOfTheSeason;

        public Builder(){
        }

        public Builder calendar(Calendar calendar){
            this.calendar = calendar;
            return this;
        }
        public Builder season(Season season){
            this.season = season;
            return this;
        }

        public Builder day(Day day){
            this.day = day;
            return this;
        }

        public Builder timeInMinutes(int timeInMinutes){
            this.timeInMinutes = timeInMinutes;
            return this;
        }

        public Builder dayOfTheSeason(int dayOfTheSeason){
            this.dayOfTheSeason = dayOfTheSeason;
            return this;
        }

        public Clock build(){
            Objects.requireNonNull(calendar, "Cannot create a clock if the calendar is null");
            Objects.requireNonNull(season, "Cannot create a clock if the current season is null");
            Objects.requireNonNull(day, "Cannot create a clock if the current season is null");
            if(timeInMinutes < 0 || MINUTES_PER_DAY <= timeInMinutes){
                throw new IllegalArgumentException("Clock's time is invalid");
            }
            if(dayOfTheSeason <= 0 || calendar.getSeasonLength(season) < dayOfTheSeason){
                throw new IllegalArgumentException("Clock's current day in the current season is invalid");
            }
            return new Clock(this);
        }
    }
}
