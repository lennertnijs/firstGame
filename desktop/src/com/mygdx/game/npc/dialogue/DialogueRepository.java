package com.mygdx.game.npc.dialogue;

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
        // should each list of new inputs be mapped?
        this.map = new HashMap<>(map);
    }

    public ResponseData getResponseData(String input){
        Objects.requireNonNull(input, "input is null.");
        if(!map.containsKey(input))
            throw new NoSuchElementException("input is not mapped.");
        return map.get(input);
    }
}
