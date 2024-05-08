package com.mygdx.game.Dialogue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.NoSuchElementException;

public final class DialogueRepository{

    private final Map<String, ResponseData> map;

    public DialogueRepository(Map<String, ResponseData> map){
        Objects.requireNonNull(map, "Map is null.");
        if(map.containsKey(null) || map.containsValue(null))
            throw new NullPointerException("Map contains a null key or value.");
        this.map = new HashMap<>(map);
    }

    public ResponseData getResponseData(String input){
        Objects.requireNonNull(input, "Input is null.");
        if(!map.containsKey(input))
            throw new NoSuchElementException("Input is not mapped.");
        return map.get(input);
    }
}
