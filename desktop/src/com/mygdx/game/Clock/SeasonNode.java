package com.mygdx.game.Clock;

import com.mygdx.game.LinkedList.Node;

import static com.mygdx.game.ArgumentValidator.ifNullThrowError;

public class SeasonNode extends Node {
    private final Season season;

    public SeasonNode(Builder builder){
        super();
        this.season = builder.season;
    }

    public Season getSeason(){
        return this.season;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private Season season;

        public Builder(){

        }

        public Builder setSeason(Season season){
            this.season = season;
            return this;
        }

        public SeasonNode build(){
            ifNullThrowError(season, "Cannot build a season node with a null season");
            return new SeasonNode(this);
        }
    }
}
