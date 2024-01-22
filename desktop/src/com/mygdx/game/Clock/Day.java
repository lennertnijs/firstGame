package com.mygdx.game.Clock;

import static com.mygdx.game.ArgumentValidator.ifNullThrowError;
import static com.mygdx.game.Constants.HOURS_PER_DAY;

// TESTED

public class Day {

    private final DayName dayName;
    private final int lengthInHours = HOURS_PER_DAY;

    public Day(Builder builder){
        this.dayName = builder.dayName;
    }

    public DayName getDayName(){
        return this.dayName;
    }

    public int getLengthInHours(){
        return this.lengthInHours;
    }

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private DayName dayName;

        public Builder(){
        }

        public Builder dayName(DayName dayName){
            this.dayName = dayName;
            return this;
        }

        public Day build(){
            ifNullThrowError(dayName, "Cannot create a day object with a null name");
            return new Day(this);
        }
    }
}
