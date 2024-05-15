package com.mygdx.game.TextureSelector;

import com.mygdx.game.WeekSchedule.Action;
import com.mygdx.game.Util.Direction;

import java.util.Objects;

public final class Key {

    private final Action action;
    private final Direction direction;

    public Key(Action action, Direction direction){
        this.action = Objects.requireNonNull(action, "Action is null.");
        this.direction = Objects.requireNonNull(direction, "Direction is null.");
    }

    public Action action(){
        return action;
    }

    public Direction direction(){
        return direction;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Key))
            return false;
        Key key = (Key) other;
        return action == key.action && direction == key.direction;
    }

    @Override
    public int hashCode(){
        return action.hashCode() * 31 + direction.hashCode();
    }

    @Override
    public String toString(){
        return String.format("Key[Action=%s, Direction=%s]", action, direction);
    }

}
