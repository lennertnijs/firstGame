package game.npc.dialogue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class DialogueData{

    private final List<String> activeInputs;
    private final DialogueRepository repository;

    public DialogueData(List<String> activeInputs, DialogueRepository repository){
        Objects.requireNonNull(activeInputs, "List is null.");
        if(activeInputs.contains(null)) {
            throw new NullPointerException("List contains null.");
        }
        Objects.requireNonNull(repository, "dialogue repository is null.");
        for(String input : activeInputs) {
            repository.getResponseData(input); // throws NoSuchElementException if not mapped
        }
        this.activeInputs = new ArrayList<>(activeInputs);
        this.repository = repository;
    }

    public List<String> getActiveInputs(){
        return new ArrayList<>(activeInputs);
    }

    public String processInput(String input){
        Objects.requireNonNull(input, "input is null.");
        if(!activeInputs.contains(input)) {
            throw new IllegalStateException("Not an active input.");
        }
        ResponseData responseData = repository.getResponseData(input);
        activeInputs.remove(input);
        activeInputs.addAll(responseData.newInputs());
        return responseData.response();
    }
}
