package com.mygdx.game.V2.Dialogue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.NoSuchElementException;

public final class DialogueRepository implements IDialogueRepository{

    private final Map<String, IResponseData> mapping;

    /**
     * Creates a new {@link DialogueRepository}.
     * @param mapping The mapping. Cannot be null. Cannot contain a null key or value.
     */
    public DialogueRepository(Map<String, IResponseData> mapping){
        Objects.requireNonNull(mapping, "Mapping is null.");
        if(mapping.containsKey(null))
            throw new NullPointerException("Mapping contains a null key.");
        if(mapping.containsValue(null))
            throw new NullPointerException("Mapping contains a null value.");
        this.mapping = new HashMap<>(mapping);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IResponseData getResponseData(String input){
        Objects.requireNonNull(input, "Input is null.");
        if(!mapping.containsKey(input))
            throw new NoSuchElementException("Input is not mapped.");
        return mapping.get(input);
    }
}
