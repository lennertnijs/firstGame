package game.npc.states;

import game.npc.navigation.Location;
import game.npc.NPC;
import game.npc.week_schedule.Activity;
import game.util.DirectionCalculator;
import game.util.MovementUtilMethods;

import java.util.List;
import java.util.Objects;

public final class WalkState implements NPCState {

    private final NPC npc;
    private final List<Location> route;
    private final Activity activity;

    public WalkState(NPC npc, List<Location> route, Activity activity){
        this.npc = Objects.requireNonNull(npc, "Npc is null.");
        this.route = route;
        this.activity = activity;
    }

    public void progress(double delta){
        int movement = (int)(delta * npc.getStats().getSpeed() / 200);
        Location current = new Location(npc.getMap(), npc.getPosition());
        Location next = MovementUtilMethods.moveAlongRoute(current, route, movement);
        npc.setPosition(next.position());
        npc.setMap(next.map());
        npc.setDirection(DirectionCalculator.calculateDir(current.position(), next.position()));
        updateState();
    }

    private void updateState() {
        if(route.isEmpty()){
            npc.changeState(new OtherState(npc, activity.activityType()));
        }
    }

    @Override
    public String getState(){
        return "walk";
    }
}
