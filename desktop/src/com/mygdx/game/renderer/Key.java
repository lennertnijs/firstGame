package com.mygdx.game.renderer;

import com.mygdx.game.Util.Direction;

import java.util.Objects;

/**
 * A mutable key that is used to identify animations.
 * A key contains:
 * - an activity
 * - a direction (in which the activity is being performed)
 */
public final class Key {

    private String activity;
    private Direction direction;

    public Key(String activity, Direction direction){
        this.activity = Objects.requireNonNull(activity);
        this.direction = Objects.requireNonNull(direction);
    }

    public String getActivity(){
        return activity;
    }

    public void setActivity(String updatedActivity){
        this.activity = Objects.requireNonNull(updatedActivity);
    }

    public Direction getDirection(){
        return direction;
    }

    public void setDirection(Direction updatedDirection){
        this.direction = Objects.requireNonNull(updatedDirection);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Key key))
            return false;
        return activity.equals(key.activity) && direction == key.direction;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = result * 31 + activity.hashCode();
        result = result * 31 + direction.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Key[activity=%s, direction=%s]", activity, direction);
    }
}
