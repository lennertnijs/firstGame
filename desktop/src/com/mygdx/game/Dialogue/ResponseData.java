package com.mygdx.game.Dialogue;

import com.mygdx.game.Action.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ResponseData{

    private final String response;
    private final List<String> newInputs;
    private final List<Action> actions;

    public ResponseData(String response, List<String> newInputs, List<Action> actions){
        Objects.requireNonNull(response, "Response is null.");
        Objects.requireNonNull(actions, "Action list is null.");
        if(actions.contains(null))
            throw new NullPointerException("Action list contains null.");
        Objects.requireNonNull(newInputs, "New input list is null.");
        if(newInputs.contains(null))
            throw new NullPointerException("New input list contains null.");
        this.response = response;
        this.newInputs = new ArrayList<>(newInputs);
        this.actions = new ArrayList<>(actions);
    }

    public String response(){
        return response;
    }

    public List<String> newInputs(){
        return new ArrayList<>(newInputs);
    }

    public List<Action> actions(){
        return new ArrayList<>(actions);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof ResponseData))
            return false;
        ResponseData data = (ResponseData) other;
        return response.equals(data.response) && actions.equals(data.actions) && newInputs.equals(data.newInputs);
    }

    @Override
    public int hashCode(){
        int result = response.hashCode();
        result = result * 31 + actions.hashCode();
        result = result * 31 + newInputs.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("ResponseData[Response=%s, NewInputs=%s, Actions=%s]", response, actions, newInputs);
    }
}
