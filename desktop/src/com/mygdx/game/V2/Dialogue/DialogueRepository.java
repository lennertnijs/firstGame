package com.mygdx.game.V2.Dialogue;

import java.util.Map;
import java.util.Objects;

public final class DialogueRepository implements IDialogueRepository{

    private final Map<String, IResponseData> mapping;

    private DialogueRepository(Map<String, IResponseData> mapping){
        this.mapping = mapping;
    }

    public static DialogueRepository create(Map<String, IResponseData> mapping){
        Objects.requireNonNull(mapping, "Cannot create a DialogueRepository with null.");
        if(mapping.containsKey(null) || mapping.containsValue(null))
            throw new NullPointerException("Cannot create a DialogueRepository with a null key or value.");
        return new DialogueRepository(mapping);
    }

    @Override
    public IResponseData getResponse(String line){
        Objects.requireNonNull(line, "The Line is null.");
        if(!mapping.containsKey(line))
            throw new NullPointerException("No mapping was found.");
        return mapping.get(line);
    }
}
