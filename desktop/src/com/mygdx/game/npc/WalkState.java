package com.mygdx.game.npc;

import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Time;
import com.mygdx.game.UtilMethods.DirectionCalculator;

public final class WalkState implements NPCState{

    private final NPC npc;

    public WalkState(NPC npc){
        this.npc = npc;
    }

    public String getState(){
        return "walk";
    }

    public void progress(Day day, Time time, double delta){
        int movement = (int) 15; // delta * speed
        Location current = new Location(npc.getMap(), npc.getPosition());
        Location next = npc.getNavigationData().calculateNextLocation(current, movement);
        Point currentPosition = npc.getPosition();
        currentPosition.setX(next.position().getX());
        currentPosition.setY(next.position().getY());
        npc.setMap(next.mapName());
        npc.setDirection(DirectionCalculator.calculate(current.position(), next.position()));
        updateState();
    }

    public void updateState() {
        if(npc.getNextActivity().position().equals(npc.getPosition())){
            npc.changeState(new OtherState(npc, npc.getNextActivity().type()));
        }
    }
}
