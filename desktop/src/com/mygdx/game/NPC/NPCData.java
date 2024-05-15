package com.mygdx.game.NPC;

import com.mygdx.game.Util.Direction;
import com.mygdx.game.WeekSchedule.Action;

import java.util.*;

public final class NPCData {

    private final Deque<Action> actionStack;
    private Direction direction;
    private double deltaInMillis;

    public NPCData(List<Action> actionList, Direction direction, double deltaInMillis){
        Objects.requireNonNull(actionList, "List is null.");
        if(actionList.contains(null)){
            throw new NullPointerException("List contains null.");
        }
        if(actionList.size() == 0){
            throw new IllegalArgumentException("List contains no elements.");
        }
        this.actionStack = new LinkedList<>(actionList);
        this.direction = Objects.requireNonNull(direction, "Direction is null.");
        if(deltaInMillis < 0){
            throw new IllegalArgumentException("Delta (in millis) is negative.");
        }
        this.deltaInMillis = deltaInMillis;
    }

    public Action getActiveAction(){
        return actionStack.peekLast();
    }

    public void addAction(Action action){
        Objects.requireNonNull(action, "Action is null.");
        if(actionStack.contains(action)){
            throw new IllegalArgumentException("Action already in the action stack.");
        }
        actionStack.addLast(action);
        resetDelta();
    }

    public void removeAction(){
        if(actionStack.size() == 1){
            throw new IllegalStateException("Action stack only has 1 action.");
        }
        actionStack.removeLast();
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
