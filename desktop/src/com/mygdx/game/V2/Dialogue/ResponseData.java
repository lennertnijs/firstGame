package com.mygdx.game.V2.Dialogue;

import java.util.List;
import java.util.Objects;

public final class ResponseData implements IResponseData {

    private final String response;
    private final List<Action> actions;
    private final List<String> nextPrompts;

    private ResponseData(Builder builder){
        this.response = builder.response;
        this.actions = builder.actions;
        this.nextPrompts = builder.nextPrompts;
    }

    public String getResponse(){
        return response;
    }

    public List<Action> getActions(){
        return actions;
    }

    public List<String> getNextPrompts(){
        return nextPrompts;
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


    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Builder(){
        }

        private String response;
        private List<Action> actions;
        private List<String> nextPrompts;

        public Builder response(String response){
            this.response = response;
            return this;
        }

        public Builder actions(List<Action> actions){
            this.actions = actions;
            return this;
        }

        public Builder nextPrompts(List<String> nextPrompts){
            this.nextPrompts = nextPrompts;
            return this;
        }

        public ResponseData build(){
            Objects.requireNonNull(response, "Cannot create a ResponseData with a null response.");
            Objects.requireNonNull(actions, "Cannot create a ResponseData with a null List of actions.");
            if(actions.contains(null))
                throw new NullPointerException("Cannot create a ResponseData with a null Action.");
            Objects.requireNonNull(nextPrompts, "Cannot create a ResponseData with a null List of next prompts.");
            if(nextPrompts.contains(null))
                throw new NullPointerException("Cannot create a ResponseData with a next prompt being null.");
            return new ResponseData(this);
        }
    }
}
