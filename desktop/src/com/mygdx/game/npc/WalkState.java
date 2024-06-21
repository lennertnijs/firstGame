package com.mygdx.game.npc;

import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Time;
import com.mygdx.game.UtilMethods.DirectionCalculator;

import java.util.Objects;

public final class WalkState implements NPCState{

    private final NPC npc;

    public WalkState(NPC npc){
        this.npc = Objects.requireNonNull(npc, "Npc is null.");
    }

    public void progress(Day day, Time time, double deltaInMillis){
        int movement = (int)(deltaInMillis * npc.getStats().getSpeed());
        Location current = npc.getLocation();
        Location next = npc.getNavigationData().calculateNextLocation(current, movement);
        npc.setLocation(next);
        npc.setDirection(DirectionCalculator.calculate(npc.getPosition(), next.position()));
        updateState();
    }

    private void updateState() {
        if(npc.getNextActivity().position().equals(npc.getPosition())){
            npc.changeState(new OtherState(npc, npc.getNextActivity().type()));
        }
    }

    @Override
    public String getState(){
        return "walk";
    }
}
