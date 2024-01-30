package com.mygdx.game.Entity;

import com.mygdx.game.Interactive.Interactive;

import java.util.Objects;

public class Entity implements Interactive {

    private Position position;

    public Entity(Position position){
        Objects.requireNonNull(position, "An entity must not have a null position");
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position){
        Objects.requireNonNull(position, "The new position of an entity must not be null");
        this.position = position;
    }

    public void interact(){

    }
}
