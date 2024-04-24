package com.mygdx.game.V2.TextureSelector;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class AnimationRepository {

    private final Map<AnimationKey, Animation> mapping;

    public AnimationRepository(Map<AnimationKey, Animation> mapping){
        Objects.requireNonNull(mapping, "Mapping is null.");
        if(mapping.containsKey(null))
            throw new NullPointerException("Mapping contains a null key.");
        if(mapping.containsValue(null))
            throw new NullPointerException("Mapping contains a null value.");
        this.mapping = new HashMap<>(mapping);
    }

    public Map<AnimationKey, Animation> mapping(){
        return new HashMap<>(mapping);
    }

    public Animation getAnimation(AnimationKey key){
        Objects.requireNonNull(key, "Animation key is null.");
        if(!mapping.containsKey(key))
            throw new NoSuchElementException("No mapping found.");
        return mapping.get(key);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof AnimationRepository))
            return false;
        AnimationRepository repository = (AnimationRepository) other;
        return mapping.equals(repository.mapping);
    }

    @Override
    public int hashCode(){
        return mapping.hashCode();
    }

    @Override
    public String toString(){
        return String.format("AnimationRepository[mapping=%s]", mapping);
    }
}
