package com.mygdx.game.V2.TextureSelector;

import com.mygdx.game.V2.Direction;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DirectionAnimationRepo<T> {

    private final Map<Direction, Animation<T>> dirAnimMap;

    public DirectionAnimationRepo(Map<Direction, Animation<T>> dirAnimMap){
        validateMap(dirAnimMap);
        this.dirAnimMap = dirAnimMap;
    }

    private void validateMap(Map<Direction, Animation<T>> map){
        Objects.requireNonNull(map, "Cannot make a DirectionAnimationRepo from null.");
        if(map.containsKey(null) || map.containsValue(null))
            throw new NullPointerException("Cannot make a DirectionAnimationRepo with a null value.");
    }

    public Map<Direction, Animation<T>> getMapping(){
        return dirAnimMap;
    }

    public Animation<T> getAnimation(Direction direction){
        Objects.requireNonNull(direction, "Cannot retrieve an Animation for null.");
        if(!dirAnimMap.containsKey(direction))
            throw new NoSuchElementException("No mapping for the given Direction exists.");
        return dirAnimMap.get(direction);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof DirectionAnimationRepo<?>))
            return false;
        DirectionAnimationRepo<?> repo = (DirectionAnimationRepo<?>) other;
        return dirAnimMap.equals(repo.dirAnimMap);
    }

    @Override
    public int hashCode(){
        return dirAnimMap.hashCode();
    }

    @Override
    public String toString(){
        return String.format("DirectionAnimationRepo[Mapping=%s]", dirAnimMap);
    }
}
