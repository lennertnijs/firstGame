package com.mygdx.game.Entity;

import java.util.Objects;

public class Entity {

    private Position position;
    final private String spritePath;

    public Entity(Position position, String spritePath){
        Objects.requireNonNull(position, "An entity must not have a null position");
        Objects.requireNonNull(spritePath, "An entity must not have a null sprite path");
        this.position = position;
        this.spritePath = spritePath;
    }

    public Position getPosition() {
        return position;
    }
    public String getSpritePath() {
        return spritePath;
    }

    public void setPosition(Position position){
        Objects.requireNonNull(position, "The new position of an entity must not be null");
        this.position = position;
    }
}
