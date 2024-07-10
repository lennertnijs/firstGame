package com.mygdx.game.npc;

import com.mygdx.game.Util.Location;
import com.mygdx.game.UtilMethods.DirectionCalculator;
import com.mygdx.game.UtilMethods.MovementUtilMethods;

import java.util.Objects;

public final class WalkState implements NPCState{

    private final NPC npc;

    public WalkState(NPC npc){
        this.npc = Objects.requireNonNull(npc, "Npc is null.");
    }

    public void progress(double delta){
        int movement = (int)(delta * npc.getStats().getSpeed() / 200);
        Location current = npc.getLocation();
        Location next = MovementUtilMethods.moveAlongRoute(current, npc.getRoute(), movement);
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
