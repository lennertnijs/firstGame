package com.mygdx.game.AnimationRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class AnimationRepository{

    private final Map<Key, Animation> map;

    public AnimationRepository(Map<Key, Animation> map){
        Objects.requireNonNull(map, "Map is null.");
        if(map.containsKey(null) || map.containsValue(null)) {
            throw new NullPointerException("Map contains a null key.");
        }
        this.map = new HashMap<>(map);
    }

    public Animation get(Key key){
        Objects.requireNonNull(key, "Animation key is null.");
        if(!map.containsKey(key)) {
            throw new NoSuchElementException("No mapping found.");
        }
        return map.get(key);
    }

    @Override
    public String toString(){
        return String.format("AnimationRepository[map=%s]", map);
    }
}
