package com.mygdx.game.Clock;

import static com.mygdx.game.ArgumentValidator.ifNullThrowError;

// TESTED

public class Season {

    final private SeasonName seasonName;
    final private int lengthInDays;

    public Season(Builder builder){
        this.seasonName = builder.seasonName;
        this.lengthInDays = builder.lengthInDays;
    }

    public SeasonName getSeasonName(){
        return this.seasonName;
    }

    public int getLengthInDays(){
        return this.lengthInDays;
    }



    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private SeasonName seasonName;
        private int lengthInDays;

        public Builder(){
        }

        public Builder seasonName(SeasonName seasonName){
            this.seasonName = seasonName;
            return this;
        }

        public Builder lengthInDays(int lengthInDays){
            this.lengthInDays = lengthInDays;
            return this;
        }

        public Season build(){
            ifNullThrowError(seasonName, "Cannot create a season with a null name");
            if(lengthInDays < 0){
                throw new IllegalArgumentException("Length of a season cannot be negative");
            }
            return new Season(this);
        }
    }
}
