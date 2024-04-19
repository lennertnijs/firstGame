package com.mygdx.game.V2.Dialogue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class DialogueData implements IDialogueData{

    private final List<Line> active;
    private final IDialogueRepository repository;

    private DialogueData(List<Line> active, IDialogueRepository repository){
        this.active = active;
        this.repository = repository;
    }

    public static DialogueData create(List<Line> active, IDialogueRepository repository){
        Objects.requireNonNull(active, "List of active lines is null.");
        if(active.contains(null))
            throw new NullPointerException("List of active Lines contains null.");
        Objects.requireNonNull(repository, "IDialogueRepository is null.");
        return new DialogueData(active, repository);
    }

    @Override
    public List<String> getActive(){
        List<String> activeStrings = new ArrayList<>();
        for(Line line : active)
            activeStrings.add(line.getText());
        return activeStrings;
    }

    @Override
    public void process(String text){
        Objects.requireNonNull(text, "Text is null.");
        DialogueLine line = DialogueLine.create(text);
        if(!active.contains(line))
            return;
        IResponseData responseData = repository.getResponse(line);
        // display response?
        for(Action action : responseData.getActions())
            action.execute();
        active.addAll( responseData.getNextPrompts());
    }
}
