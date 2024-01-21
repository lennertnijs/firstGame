package com.mygdx.game.NPC;

import com.mygdx.game.NPC.LinkedList.LinkedList;
import com.mygdx.game.NPC.LinkedList.Node;

public class DaySchedule {

    private final Day day;
    private final LinkedList activities = new LinkedList();
    public DaySchedule(Day day){
        this.day = day;
    }

    public Day getDay(){
        return this.day;
    }

    public LinkedList getActivities(){
        return this.activities;
    }

    public void addActivity(Node node){
        activities.addNode(node);
    }
}
