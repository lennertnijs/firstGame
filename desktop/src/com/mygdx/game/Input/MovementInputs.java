package com.mygdx.game.Input;

import com.mygdx.game.Util.Direction;

import java.util.Deque;
import java.util.LinkedList;

public final class MovementInputs {

    private final Deque<Direction> directionStack;

    public MovementInputs(){
        directionStack = new LinkedList<>();
    }

    public void addDirection(Direction direction){
        directionStack.add(direction);
    }

    public Direction getCurrentDirection(){
        return directionStack.peekLast();
    }

    public void removeDirection(Direction direction){
        directionStack.removeIf(d -> d == direction);
    }
}
