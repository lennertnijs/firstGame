package com.mygdx.game.NPC;

import com.mygdx.game.Clock.Day;

public class DaySchedule {

    private final Day dayName;
    private final LinkedList activities = new LinkedList();
    public DaySchedule(Day dayName){
        this.dayName = dayName;
    }

    public Day getDay(){
        return this.dayName;
    }

    public LinkedList getActivities(){
        return this.activities;
    }

    public void addActivity(Node node){
        activities.add(node);
    }
}
