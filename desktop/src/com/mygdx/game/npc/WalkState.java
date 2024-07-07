package com.mygdx.game.npc;

import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Time;
import com.mygdx.game.UtilMethods.DirectionCalculator;
import com.mygdx.game.UtilMethods.MovementUtilMethods;

import java.util.Objects;

public final class WalkState implements NPCState{

    private final NPC npc;

    public WalkState(NPC npc){
        this.npc = Objects.requireNonNull(npc, "Npc is null.");
    }

    public void progress(Day day, Time time, double deltaInMillis){
        Location current = npc.getLocation();
        Location next = MovementUtilMethods.moveAlongRoute(current, npc.getRoute(), 3);
        npc.setLocation(next);
        npc.setDirection(DirectionCalculator.calculateDir(current.position(), next.position()));
        updateState();
    }

    private void updateState() {
        if(npc.getRoute().isEmpty()){
            npc.changeState(new OtherState(npc, npc.getNextActivity().type()));
        }
    }

    @Override
    public String getState(){
        return "walk";
    }
}
