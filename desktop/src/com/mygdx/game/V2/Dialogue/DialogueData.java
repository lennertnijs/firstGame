package com.mygdx.game.V2.Dialogue;

import com.mygdx.game.V2.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class DialogueData implements IDialogueData{

    private final List<String> active;
    private final IDialogueRepository repository;

    private DialogueData(List<String> active, IDialogueRepository repository){
        this.active = active;
        this.repository = repository;
    }

    public static DialogueData create(List<String> active, IDialogueRepository repository){
        Objects.requireNonNull(active, "List of active lines is null.");
        if(active.contains(null))
            throw new NullPointerException("List of active Lines contains null.");
        Objects.requireNonNull(repository, "IDialogueRepository is null.");
        return new DialogueData(active, repository);
    }

    @Override
    public List<String> getActive(){
        return new ArrayList<>(active);
    }

    @Override
    public void process(String line){
        Objects.requireNonNull(line, "Text is null.");
        if(!active.contains(line))
            return;
        IResponseData responseData = repository.getResponseData(line);
        // display response?
        for(Action action : responseData.getActions())
            action.execute();
        active.addAll(responseData.getNewInputs());
    }
}
