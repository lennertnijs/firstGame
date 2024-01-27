package com.mygdx.game.Clock;

import java.util.Map;
import java.util.Objects;

public class Calendar {
    private final Map<Season, Integer> seasons;

    public Calendar(Builder builder){
        this.seasons = builder.seasons;
    }

    public Map<Season, Integer> getSeasons(){
        return seasons;
    }

    public int amountOfSeasons(){
        return seasons.size();
    }

    public int getSeasonLength(Season season){
        Objects.requireNonNull(season, "Cannot find season length for a null season");
        return seasons.get(season);
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Calendar)){
            return false;
        }
        Calendar calendar = (Calendar) o;
        return seasons.equals(calendar.seasons);
    }

    @Override
    public int hashCode(){
        return Objects.hash(seasons);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Map<Season, Integer> seasons = null;

        public Builder seasons(Map<Season, Integer> seasons){
            this.seasons = seasons;
            return this;
        }

        public Calendar build(){
            Objects.requireNonNull(seasons, "Cannot build a calendar with a null map");
            for(Season season: seasons.keySet()){
                Objects.requireNonNull(season, "Null season not allowed in calendar");
                Objects.requireNonNull(seasons.get(season), "Null lengths not allowed in the calendar");
                if(seasons.get(season) < 0){
                    throw new IllegalArgumentException("The length of a season cannot be negative");
                }
            }
            return new Calendar(this);
        }
    }
}
