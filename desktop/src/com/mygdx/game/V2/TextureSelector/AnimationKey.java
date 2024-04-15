package com.mygdx.game.V2.TextureSelector;

import com.mygdx.game.V2.Util.ActivityType;
import com.mygdx.game.V2.Util.Direction;

import java.util.Objects;

public final class AnimationKey {

    private final ActivityType activityType;
    private final Direction direction;

    private AnimationKey(ActivityType activityType, Direction direction){
        this.activityType = activityType;
        this.direction = direction;
    }

    public static AnimationKey create(ActivityType activityType, Direction direction){
        Objects.requireNonNull(activityType, "Cannot create an AnimationKey with a null ActivityType.");
        Objects.requireNonNull(direction, "Cannot create an AnimationKey with a null Direction.");
        return new AnimationKey(activityType, direction);
    }

    public ActivityType getActivityType(){
        return activityType;
    }

    public Direction getDirection(){
        return direction;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof AnimationKey))
            return false;
        AnimationKey key = (AnimationKey) other;
        return activityType == key.activityType && direction == key.direction;
    }

    @Override
    public int hashCode(){
        int result = activityType.hashCode();
        result = result * 31 + direction.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("AnimationKey[ActivityType=%s, Direction=%s]", activityType, direction);
    }

}
