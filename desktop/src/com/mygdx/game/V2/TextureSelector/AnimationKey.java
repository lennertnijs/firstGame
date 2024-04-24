package com.mygdx.game.V2.TextureSelector;

import com.mygdx.game.V2.Util.ActivityType;
import com.mygdx.game.V2.Util.Direction;

import java.util.Objects;

public final class AnimationKey {

    private final ActivityType activityType;
    private final Direction direction;

    public AnimationKey(ActivityType activityType, Direction direction){
        Objects.requireNonNull(activityType, "Activity type is null.");
        Objects.requireNonNull(direction, "Direction is null.");
        this.activityType = activityType;
        this.direction = direction;
    }

    public ActivityType activityType(){
        return activityType;
    }

    public Direction direction(){
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
