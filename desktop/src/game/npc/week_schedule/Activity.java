package game.npc.week_schedule;

import game.util.Vec2;

import java.util.Objects;

public record Activity(String activityType, String map, Vec2 position, Time time){

    public Activity {
        Objects.requireNonNull(activityType, "Activity type is null.");
        Objects.requireNonNull(map, "Game map is null.");
        Objects.requireNonNull(position, "Position is null.");
        Objects.requireNonNull(time, "Time is null.");
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Activity activity))
            return false;
        return activityType.equals(activity.activityType) &&
                map.equals(activity.map) &&
                position.equals(activity.position) &&
                time.equals(activity.time);
    }

    @Override
    public int hashCode(){
        int result = activityType.hashCode();
        result = result * 31 + map.hashCode();
        result = result * 31 + position.hashCode();
        result = result * 31 + time.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Activity[type=%s, map=%s, position=%s, %s]", activityType, map, position, time);
    }
}
