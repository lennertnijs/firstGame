package com.mygdx.game.V2.Dialogue;

import com.mygdx.game.V2.Action.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a container holding all the data for a response.
 */
public final class ResponseData{

    private final String response;
    private final List<String> newInputs;
    private final List<Action> actions;

    /**
     * Creates a new immutable {@link ResponseData}.
     * @param response The response. Cannot be null.
     * @param newInputs The new inputs. Cannot be null. Cannot contain null.
     * @param actions The actions to be executed. Cannot be null. Cannot contain null.
     */
    public ResponseData(String response, List<String> newInputs, List<Action> actions){
        Objects.requireNonNull(response, "Response is null.");
        Objects.requireNonNull(actions, "Actions list is null.");
        if(actions.contains(null))
            throw new NullPointerException("List contains a null Action.");
        Objects.requireNonNull(newInputs, "New input list is null.");
        if(newInputs.contains(null))
            throw new NullPointerException("List contains a null input.");
        this.response = response;
        this.newInputs = new ArrayList<>(newInputs);
        this.actions = new ArrayList<>(actions);
    }

    /**
     * @return The response.
     */
    public String getResponse(){
        return response;
    }

    /**
     * @return A copy of the new inputs to become available after this response.
     */
    public List<String> getNewInputs(){
        return new ArrayList<>(newInputs);
    }

    /**
     * @return A copy of the actions to be performed with this response.
     */
    public List<Action> getActions(){
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
