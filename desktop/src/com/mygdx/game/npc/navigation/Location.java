package com.mygdx.game.npc.navigation;

import com.mygdx.game.util.Vec2;

import java.util.Objects;

public record Location(String map, Vec2 position) {


    public Location {
        Objects.requireNonNull(map, "Map is null.");
        Objects.requireNonNull(position, "Position is null.");
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Location location))
            return false;
        return map.equals(location.map) && position.equals(location.position);
    }

    @Override
    public int hashCode(){
        return map.hashCode() * 31 + position.hashCode();
    }

    @Override
    public String toString(){
        return String.format("Location[mapName=%s, position=%s]", map, position);
    }
}
