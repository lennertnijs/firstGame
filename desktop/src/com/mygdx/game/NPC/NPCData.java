package com.mygdx.game.NPC;

import com.mygdx.game.Util.Direction;
import com.mygdx.game.WeekSchedule.ActivityType;

import java.util.*;

public final class NPCData {

    private final Deque<ActivityType> activityTypeStack;
    private Direction direction;
    private double deltaInMillis;

    public NPCData(List<ActivityType> activityTypeList, Direction direction, double deltaInMillis){
        Objects.requireNonNull(activityTypeList, "List is null.");
        if(activityTypeList.contains(null)){
            throw new NullPointerException("List contains null.");
        }
        if(activityTypeList.size() == 0){
            throw new IllegalArgumentException("List contains no elements.");
        }
        this.activityTypeStack = new LinkedList<>(activityTypeList);
        this.direction = Objects.requireNonNull(direction, "Direction is null.");
        if(deltaInMillis < 0){
            throw new IllegalArgumentException("Delta (in millis) is negative.");
        }
        this.deltaInMillis = deltaInMillis;
    }

    public ActivityType getActiveAction(){
        return activityTypeStack.peekLast();
    }

    public void addAction(ActivityType activityType){
        Objects.requireNonNull(activityType, "Action is null.");
        if(activityTypeStack.contains(activityType)){
            throw new IllegalArgumentException("Action already in the action stack.");
        }
        activityTypeStack.addLast(activityType);
        resetDelta();
    }

    public void removeAction(){
        if(activityTypeStack.size() == 1){
            throw new IllegalStateException("Action stack only has 1 action.");
        }
        activityTypeStack.removeLast();
    }

    public Direction getDirection(){
        return direction;
    }

    public void setDirection(Direction direction){
        this.direction = Objects.requireNonNull(direction, "Direction is null.");
    }


    public double getDelta(){
        return deltaInMillis;
    }

    public void increaseDelta(double increase){
        if(increase <= 0){
            throw new IllegalArgumentException("Increase is negative.");
        }
        deltaInMillis += increase;
    }

    private void resetDelta(){
        deltaInMillis = 0;
    }
}
