package com.mygdx.game.TextureSelector;

import com.mygdx.game.Util.ActivityType;
import com.mygdx.game.Util.Direction;

import java.util.Objects;

/**
 * Represents a key for animation retrieval.
 * A key holds an activity type and a direction.
 * IMMUTABLE
 */
public final class Key {

    /**
     * The activity type.
     */
    private final ActivityType activityType;
    /**
     * The direction.
     */
    private final Direction direction;

    /**
     * Creates a new immutable {@link Key}.
     * @param activityType The {@link ActivityType}. Cannot be null.
     * @param direction The {@link Direction}. Cannot be null.
     */
    public Key(ActivityType activityType, Direction direction){
        Objects.requireNonNull(activityType, "Activity type is null.");
        Objects.requireNonNull(direction, "Direction is null.");
        this.activityType = activityType;
        this.direction = direction;
    }

    /**
     * @return The {@link ActivityType}.
     */
    public ActivityType activityType(){
        return activityType;
    }

    /**
     * @return The {@link Direction}.
     */
    public Direction direction(){
        return direction;
    }

    /**
     * Compares this {@link Key} to the given object and returns true if they're equal. Returns false otherwise.
     * Two {@link Key}s are equal if they have the same activity type and direction.
     *
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Key))
            return false;
        Key key = (Key) other;
        return activityType == key.activityType && direction == key.direction;
    }

    /**
     * @return The hash code of this {@link Key}.
     */
    @Override
    public int hashCode(){
        int result = activityType.hashCode();
        result = result * 31 + direction.hashCode();
        return result;
    }

    /**
     * @return The string representation of this {@link Key}.
     */
    @Override
    public String toString(){
        return String.format("AnimationKey[ActivityType=%s, Direction=%s]", activityType, direction);
    }

}
