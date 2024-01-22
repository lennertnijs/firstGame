package com.mygdx.game.Clock;



import static com.mygdx.game.ArgumentValidator.ifNullThrowError;
import static com.mygdx.game.Constants.*;

public class Clock {

    private final Calendar calendar;
    private Season currentSeason;
    private Day day;
    private int timeInMinutes;
    private int dayInCurrentSeason;



    /**
     * Constructor for a {@code Clock}.
     */
    public Clock(Builder builder){
        this.calendar = builder.calendar;
        this.currentSeason = builder.currentSeason;
        this.day = builder.day;
        this.timeInMinutes = builder.timeInMinutes;
        this.dayInCurrentSeason = builder.dayInCurrentSeason;

    }

    public Calendar getCalendar(){
        return this.calendar;
    }
    public Season getSeason(){
        return this.currentSeason;
    }

    public Day getDay(){
        return this.day;
    }

    public int getTimeInMinutes(){
        return this.timeInMinutes;
    }

    public int getDayInSeason(){
        return this.dayInCurrentSeason;
    }


    /**
     * @return The time in HH:MM format. 0's will be added to keep the size of all numbers consistent.
     */
    public String getTimeInHHMM(){
        int hours = timeInMinutes/60;
        int minutes = timeInMinutes%60;
        String consistentHrs = addZerosInFront(hours, numberOfDigitsInInt(HOURS_PER_DAY-1));
        String consistentMins = addZerosInFront(minutes, numberOfDigitsInInt(MINUTES_PER_HOUR-1));
        return consistentHrs + ":" + consistentMins;
    }

    private String addZerosInFront(int number, int appropriateLength){
        if(appropriateLength < numberOfDigitsInInt(number)){
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

    public void increaseTimeByMinutes(int minutes){
        TimeValidator.validateNumberOfMinutesToAdd(minutes);
        this.timeInMinutes += minutes;
        boolean dayChange = MINUTES_PER_DAY <= this.timeInMinutes;
        if(dayChange){
            nextDay();
            this.timeInMinutes -= MINUTES_PER_DAY;
            dayInCurrentSeason += 1;
        }
        boolean seasonChange = currentSeason.getLengthInDays() < dayInCurrentSeason;
        while(seasonChange){
            nextSeason();
            seasonChange = currentSeason.getLengthInDays() < dayInCurrentSeason;
        }
    }

    private void nextDay(){
        DayName newDayName = this.day.getDayName().next();
        this.day = Day.builder().dayName(newDayName).build();
    }

    private void nextSeason(){
        SeasonName nextSeasonName = this.currentSeason.getSeasonName().next();
        dayInCurrentSeason = 1;
        this.currentSeason = calendar.getSeasonByName(nextSeasonName);
    }





    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Calendar calendar;
        private Season currentSeason;
        private Day day;
        private int timeInMinutes;
        private int dayInCurrentSeason;

        public Builder(){
        }

        public Builder calendar(Calendar calendar){
            this.calendar = calendar;
            return this;
        }
        public Builder currentSeason(Season currentSeason){
            this.currentSeason = currentSeason;
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

        public Builder dayInCurrentSeason(int dayInCurrentSeason){
            this.dayInCurrentSeason = dayInCurrentSeason;
            return this;
        }



        public Clock build(){
            ifNullThrowError(calendar, "Cannot create a clock if the calendar is null");
            ifNullThrowError(currentSeason, "Cannot create a clock if the current season is null");
            ifNullThrowError(day, "Cannot create a clock if the current season is null");
            if(timeInMinutes < 0 || MINUTES_PER_DAY <= timeInMinutes){
                throw new IllegalArgumentException("Clock's time is invalid");
            }
            if(dayInCurrentSeason <= 0 || currentSeason.getLengthInDays() < dayInCurrentSeason){
                throw new IllegalArgumentException("Clock's current day in the current season is invalid");
            }
            return new Clock(this);
        }
    }
}
