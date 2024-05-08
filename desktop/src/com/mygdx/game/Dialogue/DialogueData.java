package com.mygdx.game.Dialogue;

import com.mygdx.game.Action.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class DialogueData implements IDialogueData{

    private final List<String> activeInputs;
    private final DialogueRepository repository;

    /**
     * Creates a new {@link DialogueData}.
     * Cannot be modified externally, but modifies itself.
     * @param activeInputs The active inputs. Cannot be null. Cannot contain null.
     * @param repository The {@link DialogueRepository}. Cannot be null.
     *
     * @throws NoSuchElementException If an active input does not have a mapping in the {@link DialogueRepository}.
     */
    public DialogueData(List<String> activeInputs, DialogueRepository repository){
        Objects.requireNonNull(activeInputs, "List is null.");
        if(activeInputs.contains(null))
            throw new NullPointerException("List contains null.");
        Objects.requireNonNull(repository, "Dialogue repository is null.");
        for(String input : activeInputs)
            repository.getResponseData(input); // will throw NoSuchElementException.
        this.activeInputs = new ArrayList<>(activeInputs);
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getActiveInputs(){
        return new ArrayList<>(activeInputs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processInput(String input){
        Objects.requireNonNull(input, "Input is null.");
        if(!activeInputs.contains(input))
            throw new IllegalStateException("Not an active input.");
        activeInputs.remove(input);
        ResponseData responseData = repository.getResponseData(input);
        String response = responseData.response();
        activeInputs.addAll(responseData.newInputs());
        for(Action action : responseData.actions())
            action.execute();
    }
}
