package com.mygdx.game.npc.states;

import com.mygdx.game.npc.NPC;
import com.mygdx.game.npc.states.IdleState;
import com.mygdx.game.npc.states.NPCState;

public class OtherState implements NPCState {

    private final NPC npc;
    private final String activity;

    public OtherState(NPC npc, String activity){
        this.npc = npc;
        this.activity = activity;
    }

    public String getState(){
        return activity;
    }

    @Override
    public void progress(double delta) {
        updateState();
    }

    public void updateState(){
        npc.changeState(new IdleState(npc));
    }
}
