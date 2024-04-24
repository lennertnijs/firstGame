package com.mygdx.game.V2.TextureSelector;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Represents a repository in which keys are mapped to animations.
 */
public final class AnimationRepository {

    /**
     * The map.
     */
    private final Map<Key, Animation> map;

    /**
     * Creates a new {@link AnimationRepository}.
     * @param map The map. Cannot be null. Cannot contain a null key or value.
     */
    public AnimationRepository(Map<Key, Animation> map){
        Objects.requireNonNull(map, "Map is null.");
        if(map.containsKey(null))
            throw new NullPointerException("Map contains a null key.");
        if(map.containsValue(null))
            throw new NullPointerException("Map contains a null value.");
        this.map = new HashMap<>(map);
    }

    /**
     * @return A copy of the map.
     */
    public Map<Key, Animation> map(){
        return new HashMap<>(map);
    }

    /**
     * Fetches and returns the {@link Animation} for the given {@link Key}.
     * @param key The {@link Key}. Cannot be null.
     *
     * @return The {@link Animation}.
     * @throws NoSuchElementException If no mapping was found for the given {@link Key}.
     */
    public Animation get(Key key){
        Objects.requireNonNull(key, "Animation key is null.");
        if(!map.containsKey(key))
            throw new NoSuchElementException("No mapping found.");
        return map.get(key);
    }

    /**
     * Compares this {@link AnimationRepository} to the given object and returns true if they're equal.
     * Returns false otherwise.
     * Two {@link AnimationRepository} objects are equal if they hold the same (key, value) pairs.
     *
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof AnimationRepository))
            return false;
        AnimationRepository repository = (AnimationRepository) other;
        return map.equals(repository.map);
    }

    /**
     * @return The hash code of this {@link AnimationRepository}.
     */
    @Override
    public int hashCode(){
        return map.hashCode();
    }

    /**
     * @return The string representation of this {@link AnimationRepository}.
     */
    @Override
    public String toString(){
        return String.format("AnimationRepository[map=%s]", map);
    }
}
