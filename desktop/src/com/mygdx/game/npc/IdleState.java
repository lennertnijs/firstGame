package com.mygdx.game.npc;

import com.mygdx.game.Util.Activity;
import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Time;

import java.util.Objects;

public final class IdleState implements NPCState{

    private final NPC npc;

    public IdleState(NPC npc){
        this.npc = Objects.requireNonNull(npc, "Npc is null.");
    }

    @Override
    public void progress(Day day, Time time, double delta) {
        Activity activity = npc.getWeekSchedule().getActivity(day, time);
        if(activity == null)
            return;
        npc.setNextActivity(activity);
        npc.updateRoute(activity);
        updateState();
    }

    public void updateState(){
        if(npc.getNextActivity() == null){
            return;
        }
        if(npc.getRoute().isEmpty()){
            npc.changeState(new OtherState(npc, npc.getNextActivity().type()));
        }
        npc.changeState(new WalkState(npc));
    }

    @Override
    public String getState(){
        return "idle";
    }
}
