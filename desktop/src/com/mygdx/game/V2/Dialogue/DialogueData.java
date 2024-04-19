package com.mygdx.game.V2.Dialogue;

import com.mygdx.game.V2.Action.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class DialogueData implements IDialogueData{

    private final List<String> activeInputs;
    private final IDialogueRepository repository;

    /**
     * Creates a new {@link DialogueData}.
     * Cannot be modified, but it modifies itself.
     * @param activeInputs The active inputs. Cannot be null. Cannot contain null.
     * @param repository The {@link IDialogueRepository}. Cannot be null.
     *
     * @throws NoSuchElementException If an active input does not have a mapping in the {@link DialogueRepository}.
     */
    public DialogueData(List<String> activeInputs, IDialogueRepository repository){
        Objects.requireNonNull(activeInputs, "Active inputs is null.");
        if(activeInputs.contains(null))
            throw new NullPointerException("List contains a null input.");
        for(String input : activeInputs){
            repository.getResponseData(input);
        }
        Objects.requireNonNull(repository, "Dialogue repository is null.");
        this.activeInputs = activeInputs;
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
    public void process(String input){
        Objects.requireNonNull(input, "Input is null.");
        if(!activeInputs.contains(input))
            return;
        activeInputs.remove(input);
        IResponseData responseData = repository.getResponseData(input);
        String response = responseData.getResponse();
        for(Action action : responseData.getActions())
            action.execute();
        activeInputs.addAll(responseData.getNewInputs());
    }
}
