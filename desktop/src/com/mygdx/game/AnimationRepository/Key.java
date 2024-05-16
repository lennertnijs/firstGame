package com.mygdx.game.AnimationRepository;

import com.mygdx.game.WeekSchedule.ActivityType;
import com.mygdx.game.Util.Direction;

import java.util.Objects;

public final class Key {

    private final ActivityType activityType;
    private final Direction direction;

    public Key(ActivityType activityType, Direction direction){
        this.activityType = Objects.requireNonNull(activityType, "Action is null.");
        this.direction = Objects.requireNonNull(direction, "Direction is null.");
    }

    public ActivityType action(){
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
        return activityType.hashCode() * 31 + direction.hashCode();
    }

    @Override
    public String toString(){
        return String.format("Key[Action=%s, Direction=%s]", activityType, direction);
    }

}
