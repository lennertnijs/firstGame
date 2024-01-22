package com.mygdx.game.Clock;

import com.mygdx.game.LinkedList.LinkedList;

import static com.mygdx.game.ArgumentValidator.ifNullThrowError;

public class Calendar {
    private final LinkedList seasons = new LinkedList();

    public Calendar(){
    }

    public LinkedList getSeasons(){
        return seasons;
    }

    public int size(){
        return seasons.size();
    }

    public void addSeason(Season season){
        ifNullThrowError(season, "Cannot add a null season to the calendar.");
        SeasonNode node = SeasonNode.builder().setSeason(season).build();
        seasons.add(node);
    }

    public Season getSeasonByName(SeasonName seasonName){
        ifNullThrowError(seasonName, "Cannot look for null season in the calendar.");
        SeasonNode current = (SeasonNode) seasons.getHead();
        if(current.getSeason().getSeasonName().equals(seasonName)){
            return current.getSeason();
        }
        while(current.hasNext() && !current.next().equals(seasons.getHead())){
            current = (SeasonNode)current.next();
            if(current.getSeason().getSeasonName().equals(seasonName)){
                return current.getSeason();
            }
        }
        return null;
    }

    public boolean containsSeason(SeasonName seasonName){
        if(null == getSeasonByName(seasonName)){
            return false;
        }
        return true;
    }

}
