package com.mygdx.game.V2.WeekSchedule;

import com.mygdx.game.V2.Util.ActivityType;
import com.mygdx.game.V2.Util.Location;
import com.mygdx.game.V2.Util.Time;

import java.util.Objects;

/**
 * Represents a concrete activity.
 * IMMUTABLE
 */
public final class Activity{

    /**
     * The location.
     */
    private final Location location;
    /**
     * The time.
     */
    private final Time time;
    /**
     * The activity type.
     */
    private final ActivityType type;

    /**
     * Creates a new immutable {@link Activity}.
     * @param location The {@link Location}. Cannot be null.
     * @param time The {@link Time}. Cannot be null.
     * @param activityType The {@link ActivityType}. Cannot be null.
     */
    public Activity(Location location, Time time, ActivityType activityType){
        Objects.requireNonNull(location, "Location is null.");
        Objects.requireNonNull(time, "Time is null.");
        Objects.requireNonNull(activityType, "Activity type is null.");
        this.location = location;
        this.time = time;
        this.type = activityType;
    }

    /**
     * @return The {@link Location}.
     */
    public Location location(){
        return location;
    }

    /**
     * @return The {@link Time}.
     */
    public Time time(){
        return time;
    }

    /**
     * @return The {@link ActivityType}.
     */
    public ActivityType type(){
        return type;
    }

    /**
     * Compares this {@link Activity} to the given object and returns true if they're equal. Returns false otherwise.
     * Two {@link Activity} objects are equal if they have the same location, time & type.
     *
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Activity))
            return false;
        Activity activity = (Activity) other;
        return location.equals(activity.location) && time.equals(activity.time) && type.equals(activity.type);
    }

    /**
     * @return The hash code of this {@link Activity}.
     */
    @Override
    public int hashCode(){
        int result = location.hashCode();
        result = result * 31 + time.hashCode();
        result = result * 31 + type.hashCode();
        return result;
    }

    /**
     * @return The string representation of this {@link Activity}.
     */
    @Override
    public String toString(){
        return String.format("Activity[%s, %s, Type=%s]", location, time, type);
    }
}
