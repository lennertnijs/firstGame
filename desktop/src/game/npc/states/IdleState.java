package game.npc.states;

import game.npc.NPC;
import game.npc.navigation.Location;
import game.npc.week_schedule.Activity;

import java.util.List;
import java.util.Objects;

public final class IdleState implements NPCState {

    private final NPC npc;

    public IdleState(NPC npc){
        this.npc = Objects.requireNonNull(npc, "Npc is null.");
    }

    @Override
    public void progress(double delta) {
        updateState();
    }

    public void updateState(){
        Activity nextActivity = npc.getWeekSchedule().getActivity(null, null); // todo
        if(nextActivity == null){
            return;
        }
        Location current = new Location(npc.getMap(), npc.getPosition());
        Location next = new Location(nextActivity.map(), nextActivity.position());
        List<Location> route = npc.getNavigationData().generateRoute(current, next);
        if(route.isEmpty()){
            npc.changeState(new OtherState(npc, nextActivity.activityType()));
        }else{
            npc.changeState(new WalkState(npc, route, nextActivity));
        }
    }

    @Override
    public String getState(){
        return "idle";
    }
}
