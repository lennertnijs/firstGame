package com.mygdx.game.npc;

import com.mygdx.game.Util.Activity;
import com.mygdx.game.Util.Day;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Time;

import java.util.Objects;

public final class IdleState implements NPCState{

    private final NPC npc;

    public IdleState(NPC npc){
        this.npc = Objects.requireNonNull(npc, "Npc is null.");
    }

    public String getState(){
        return "idle";
    }

    @Override
    public void progress(Day day, Time time, double delta) {
        Activity activity = npc.getWeekSchedule().getActivity(day, time);
        if(activity == null)
            return;
        npc.setNextActivity(activity);
        Location current = new Location(npc.getMap(), npc.getPosition()); // take the end of the route, if any still active
        Location next = new Location(activity.map(), activity.position());
        npc.getNavigationData().calculateAndStoreRoute(current, next);
        updateState();
    }

    public void updateState(){
        if(npc.getNextActivity() == null){
            return;
        }
        if(npc.getPosition().equals(npc.getNextActivity().position())){
            npc.changeState(new OtherState(npc, npc.getNextActivity().type()));
        }
        npc.changeState(new WalkState(npc));
    }
}
