package com.mygdx.game.WeekSchedule;

import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Time;

import java.util.Objects;

public final class Activity{

    private final Location location;
    private final Time time;
    private final ActivityType type;

    public Activity(Location location, Time time, ActivityType type){
        Objects.requireNonNull(location, "Location is null.");
        Objects.requireNonNull(time, "Time is null.");
        Objects.requireNonNull(type, "Activity type is null.");
        this.location = location;
        this.time = time;
        this.type = type;
    }

    public Location location(){
        return location;
    }

    public Time time(){
        return time;
    }

    public ActivityType type(){
        return type;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Activity))
            return false;
        Activity activity = (Activity) other;
        return location.equals(activity.location) && time.equals(activity.time) && type == activity.type;
    }

    @Override
    public int hashCode(){
        int result = location.hashCode();
        result = result * 31 + time.hashCode();
        result = result * 31 + type.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Activity[%s, %s, Type=%s]", location, time, type);
    }
}
