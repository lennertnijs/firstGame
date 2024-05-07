package com.mygdx.game.Dialogue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.NoSuchElementException;

/**
 * Maps inputs to response data.
 */
public final class DialogueRepository{

    private final Map<String, ResponseData> map;

    /**
     * Creates a new immutable {@link DialogueRepository}.
     * @param map The map. Cannot be null. Cannot contain a null key or value.
     */
    public DialogueRepository(Map<String, ResponseData> map){
        Objects.requireNonNull(map, "Map is null.");
        if(map.containsKey(null))
            throw new NullPointerException("Map contains a null key.");
        if(map.containsValue(null))
            throw new NullPointerException("Map contains a null value.");
        this.map = new HashMap<>(map);
    }

    /**
     * Fetches and returns the {@link ResponseData} for the given input line.
     * @param input The input line. Cannot be null.
     *
     * @return The {@link ResponseData}.
     * @throws NoSuchElementException If no mapping exists for the given input line.
     */
    public ResponseData getResponseData(String input){
        Objects.requireNonNull(input, "Input is null.");
        if(!map.containsKey(input))
            throw new NoSuchElementException("Input is not mapped.");
        return map.get(input);
    }
}
