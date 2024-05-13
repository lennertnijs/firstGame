package com.mygdx.game.TextureSelector;

import com.mygdx.game.WeekSchedule.ActivityType;
import com.mygdx.game.Util.Direction;

import java.util.Objects;

public final class Key {

    private final ActivityType activityType;
    private final Direction direction;

    public Key(ActivityType activityType, Direction direction){
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
        if(!(other instanceof Key))
            return false;
        Key key = (Key) other;
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
