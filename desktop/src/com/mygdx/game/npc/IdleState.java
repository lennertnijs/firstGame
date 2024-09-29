package com.mygdx.game.npc;

import java.util.Objects;

public final class IdleState implements NPCState{

    private final NPC npc;

    public IdleState(NPC npc){
        this.npc = Objects.requireNonNull(npc, "Npc is null.");
    }

    @Override
    public void progress(double delta) {
        updateState();
    }

    public void updateState(){
        if(npc.getNextActivity() == null){
            return;
        }
        if(npc.getRoute().isEmpty()){
            npc.changeState(new OtherState(npc, npc.getNextActivity().activityType()));
        }
        npc.changeState(new WalkState(npc));
    }

    @Override
    public String getState(){
        return "idle";
    }
}
