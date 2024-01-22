package com.mygdx.game.NPC;

import com.mygdx.game.Clock.DayName;
import com.mygdx.game.LinkedList.LinkedList;
import com.mygdx.game.LinkedList.Node;

public class DaySchedule {

    private final DayName dayName;
    private final LinkedList activities = new LinkedList();
    public DaySchedule(DayName dayName){
        this.dayName = dayName;
    }

    public DayName getDay(){
        return this.dayName;
    }

    public LinkedList getActivities(){
        return this.activities;
    }

    public void addActivity(Node node){
        activities.add(node);
    }
}
