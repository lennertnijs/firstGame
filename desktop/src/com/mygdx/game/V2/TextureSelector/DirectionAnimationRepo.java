package com.mygdx.game.V2.TextureSelector;

import com.mygdx.game.V2.Direction;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DirectionAnimationRepo<T> {

    private final Map<Direction, Animation<T>> animationsPerDirection;

    private DirectionAnimationRepo(Map<Direction, Animation<T>> animationsPerDirection){
        this.animationsPerDirection = animationsPerDirection;
    }

    public static <T> DirectionAnimationRepo<T> create(Map<Direction, Animation<T>> animationsPerDirection){
        Objects.requireNonNull(animationsPerDirection, "Cannot make a DirectionAnimationRepo from null.");
        if(animationsPerDirection.containsKey(null) || animationsPerDirection.containsValue(null))
            throw new NullPointerException("Cannot make a DirectionAnimationRepo with a null value.");
        return new DirectionAnimationRepo<>(animationsPerDirection);
    }

    public Animation<T> getAnimation(Direction direction){
        Objects.requireNonNull(direction, "Cannot retrieve an Animation with null.");
        if(!animationsPerDirection.containsKey(direction))
            throw new NoSuchElementException("No Animation was found for the given Direction.");
        return animationsPerDirection.get(direction);
    }
}
