package com.mygdx.game.Clock;

import java.util.HashMap;

import static com.mygdx.game.ArgumentValidator.ifIntegerIsNegativeThrowError;
import static com.mygdx.game.ArgumentValidator.ifNullThrowError;

public class Calendar {
    private final HashMap<Season, Integer> seasons;

    public Calendar(Builder builder){
        this.seasons = builder.seasons;
    }

    public int size(){
        return seasons.size();
    }

    public int getSeasonLength(Season season){
        ifNullThrowError(season, "Cannot look for null season in the calendar.");
        return seasons.get(season);
    }



    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{

        private final HashMap<Season, Integer> seasons = new HashMap<>();

        public Builder put(Season season, int length){
            seasons.put(season, length);
            return this;
        }

        public Calendar build(){
            for(Season season: seasons.keySet()){
                ifNullThrowError(season, "Null season not allowed in calendar");
                ifIntegerIsNegativeThrowError(seasons.get(season), "Season length cannot be negative");
            }
            return new Calendar(this);
        }
    }
}
