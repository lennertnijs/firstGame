package com.mygdx.game.Keys;

import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.Util.Direction;

import java.util.Objects;

public final class EntityKey implements AnimationKey {

    private final ActivityType activityType;
    private final Direction direction;

    public EntityKey(ActivityType activityType, Direction direction){
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
        if(!(other instanceof EntityKey))
            return false;
        EntityKey entityKey = (EntityKey) other;
        return activityType == entityKey.activityType && direction == entityKey.direction;
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
