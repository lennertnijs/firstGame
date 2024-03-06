package com.mygdx.game.Clock;

import com.mygdx.game.Validator.Validator;

import java.util.*;

public class Calendar {
    private final List<Season> seasons;

    private Calendar(List<Season> seasons){
        this.seasons = seasons;
    }


    public static Calendar create(List<Season> seasons){
        Validator.notNullAndNotContainsNull(seasons, "Cannot create a Calendar with a null Season.");
        return new Calendar(seasons);
    }

    public List<Season> getSeasons(){
        return seasons;
    }

    public int getAmountOfSeasons(){
        return seasons.size();
    }

    public int getSeasonLength(SeasonName name){
        Validator.notNull(name, "Cannot find Season length for a null Season");
        for(Season season : seasons){
            if(season.getName() == name){
                return season.getLength();
            }
        }
        throw new NoSuchElementException("No Season with this name was found.");
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
        int result = 0;
        for(Season season : seasons){
            result = 31 * result + season.hashCode();
        }
        return result;
    }

    @Override
    public String toString(){
        return String.format("Calendar[Seasons = %s]", seasons);
    }
}
