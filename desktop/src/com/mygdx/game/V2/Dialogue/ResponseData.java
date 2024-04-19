package com.mygdx.game.V2.Dialogue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ResponseData implements IResponseData {

    private final String response;
    private final List<String> nextPrompts;
    private final List<Action> actions;


    public ResponseData(String response, List<Action> actions, List<String> nextPrompts){
        Objects.requireNonNull(response, "Response is null.");
        Objects.requireNonNull(actions, "Actions List is null.");
        if(actions.contains(null))
            throw new NullPointerException("List contains a null Action.");
        Objects.requireNonNull(nextPrompts, "Next prompts is null.");
        if(nextPrompts.contains(null))
            throw new NullPointerException("List contains a null next prompt.");
        this.response = response;
        this.actions = new ArrayList<>(actions);
        this.nextPrompts = new ArrayList<>(nextPrompts);
    }

    public String getResponse(){
        return response;
    }

    public List<String> getNextPrompts(){
        return nextPrompts;
    }

    public List<Action> getActions(){
        return actions;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof ResponseData))
            return false;
        ResponseData data = (ResponseData) other;
        return response.equals(data.response) &&
                actions.equals(data.actions) &&
                nextPrompts.equals(data.nextPrompts);
    }

    @Override
    public int hashCode(){
        int result = response.hashCode();
        result = result * 31 + actions.hashCode();
        result = result * 31 + nextPrompts.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("ResponseData[Response=%s, Actions=%s, NextPrompt=%s]",
                response, actions, nextPrompts);
    }
}
