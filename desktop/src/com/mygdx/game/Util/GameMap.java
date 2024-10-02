package com.mygdx.game.Util;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

public record GameMap(String name, Texture texture) {

    public GameMap {
        Objects.requireNonNull(name, "Map name cannot be null.");
        Objects.requireNonNull(texture, "Map texture cannot be null.");
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof GameMap gameMap))
            return false;
        return name.equals(gameMap.name);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public String toString(){
        return String.format("GameMap[name=%s, texture=%s]", name, texture);
    }
}
