package com.mygdx.game.V2.TextureSelector;

import com.mygdx.game.V2.Direction;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class DirectionAnimationRepo<T> implements IDirectionAnimationRepository<T>{

    private final Map<Direction, Animation<T>> directionMapping;

    public DirectionAnimationRepo(Map<Direction, Animation<T>> directionMapping){
        validateMap(directionMapping);
        this.directionMapping = directionMapping;
    }

    private void validateMap(Map<Direction, Animation<T>> map){
        Objects.requireNonNull(map, "Cannot make a DirectionAnimationRepo from null.");
        if(map.containsKey(null) || map.containsValue(null))
            throw new NullPointerException("Cannot make a DirectionAnimationRepo with a null value.");
    }

    public Animation<T> getAnimation(Direction direction){
        Objects.requireNonNull(direction, "Cannot retrieve an Animation for null.");
        if(!directionMapping.containsKey(direction))
            throw new NoSuchElementException("No mapping for the given Direction exists.");
        return directionMapping.get(direction);
    }
}
