package com.mygdx.game.Util;

import java.util.Objects;

public final class Activity{

    private final String type;
    private final String map;
    private final Point position;
    private final Time time;

    public Activity(String activityType, String map, Point position, Time time){
        this.type = Objects.requireNonNull(activityType, "Activity type is null.");
        this.map = Objects.requireNonNull(map, "Game map is null.");
        this.position = Objects.requireNonNull(position, "Position is null.");
        this.time = Objects.requireNonNull(time, "Time is null.");
    }

    public String type(){
        return type;
    }

    public String map(){
        return map;
    }

    public Point position(){
        return position;
    }

    public Time time(){
        return time;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Activity))
            return false;
        Activity activity = (Activity) other;
        return type.equals(activity.type) &&
                map.equals(activity.map) &&
                position.equals(activity.position) &&
                time.equals(activity.time);
    }

    @Override
    public int hashCode(){
        int result = type.hashCode();
        result = result * 31 + map.hashCode();
        result = result * 31 + position.hashCode();
        result = result * 31 + time.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Activity[type=%s, map=%s, position=%s, %s]", type, map, position, time);
    }
}
