package com.mygdx.game.map;

import java.util.Arrays;
import java.util.Objects;

public record SingleTextureMap(String name, int[][] tiles){

    public SingleTextureMap {
        Objects.requireNonNull(name, "Map name cannot be null.");
        Objects.requireNonNull(tiles, "Map texture cannot be null.");
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof SingleTextureMap gameMap))
            return false;
        return name.equals(gameMap.name);
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }

    @Override
    public String toString(){
        return String.format("GameMap[name=%s, texture=%s]", name, Arrays.deepToString(tiles));
    }
}
