package com.mygdx.game.npc;

import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Time;

public class OtherState implements NPCState{

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
    public void progress(Day day, Time time, double delta) {
        updateState();
    }

    public void updateState() {
        npc.changeState(new IdleState(npc));
    }
}
