package com.mygdx.game.Clock;

import java.util.Map;
import java.util.Objects;

public class Calendar {
    private final Map<Season, Integer> seasons;

    protected Calendar(Builder builder){
        this.seasons = builder.seasons;
    }

    /**
     * @return The map of {@code Season}s and their respective lengths.
     */
    public Map<Season, Integer> getSeasons(){
        return seasons;
    }

    /**
     * @return The amount of {@code Season}'s in the {@code Calendar}.
     */
    public int getAmountOfSeasons(){
        return seasons.size();
    }

    /**
     * Finds and returns the given {@code Season}'s length, if it exists.
     * @param season The {@code Season}
     * @return The {@code Season} length if the {@code Season} is on the {@code Calendar}. Returns {@code null} otherwise.
     */
    protected int getSeasonLength(Season season){
        Objects.requireNonNull(season, "Cannot find season length for a null season");
        return Objects.requireNonNull(seasons.get(season), "No season length was found");
    }

    /**
     * Compares this {@code Season} with the object.
     * @param o The object to compare to this {@code Season}
     * @return True if equal, false otherwise.
     */
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

    /**
     * @return The hash code
     */
    @Override
    public int hashCode(){
        return Objects.hash(seasons);
    }

    @Override
    public String toString(){
        String calendarString = "";
        for(Season season: seasons.keySet()){
            calendarString += season + ": " + seasons.get(season) + " ,";
        }
        return calendarString;
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
            Objects.requireNonNull(seasons, "Cannot build a calendar with a null season map");
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
