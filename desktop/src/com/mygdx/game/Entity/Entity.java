package com.mygdx.game.Entity;

import com.mygdx.game.NPC.Position2D;

import java.util.Objects;

public class Entity {

    private Position2D position;
    final private String spritePath;

    public Entity(Builder builder){
        this.position = builder.position;
        this.spritePath = builder.spritePath;
    }

    public Position2D getPosition() {
        return position;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Position2D position;
        private String spritePath;

        public Builder position(Position2D position){
            this.position = position;
            return this;
        }

        public Builder spritePath(String spritePath){
            this.spritePath = spritePath;
            return this;
        }

        public Entity build(){
            Objects.requireNonNull(position);
            Objects.requireNonNull(spritePath);
            return new Entity(this);
        }
    }
}
