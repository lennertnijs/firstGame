package game.npc.states;

import game.npc.NPC;
import game.npc.week_schedule.Time;
import game.util.Day;

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
    public void progress(double delta, Day day, Time time) {
        updateState();
    }

    public void updateState(){
        npc.changeState(new IdleState(npc));
    }
}
