package com.mygdx.game.GameObject;

import com.mygdx.game.Util.Direction;

import java.util.Objects;

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
        return activity.hashCode() * 31 + direction.hashCode();
    }

    @Override
    public String toString(){
        return String.format("Key[activity=%s, direction=%s]", activity, direction);
    }
}
