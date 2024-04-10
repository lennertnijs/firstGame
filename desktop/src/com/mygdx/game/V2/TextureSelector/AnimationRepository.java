package com.mygdx.game.V2.TextureSelector;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class AnimationRepository<T> implements IAnimationRepository<T>{

    private final Map<AnimationKey, IAnimation<T>> mapping;

    public AnimationRepository(Map<AnimationKey, IAnimation<T>> map){
        validateMap(map);
        this.mapping = map;
    }

    private void validateMap(Map<AnimationKey, IAnimation<T>> map){
        Objects.requireNonNull(map, "Cannot make a AnimationRepository from null.");
        if(map.containsKey(null) || map.containsValue(null))
            throw new NullPointerException("Cannot make an AnimationRepository with a null key or value.");
    }

    public IAnimation<T> getAnimation(AnimationKey key){
        Objects.requireNonNull(key, "Cannot retrieve an Animation for a null AnimationKey.");
        if(!mapping.containsKey(key))
            throw new NoSuchElementException("No Animation for the given key exists.");
        return mapping.get(key);
    }

}
