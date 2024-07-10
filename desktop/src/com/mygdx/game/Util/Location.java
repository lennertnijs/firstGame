package com.mygdx.game.Util;

import java.util.Objects;

public final class Location {

    private final String map;
    private final Point position;

    public Location(String map, Point position){
        Objects.requireNonNull(map, "Map is null.");
        Objects.requireNonNull(position, "Position is null.");
        this.map = map;
        this.position = position;
    }

    public String mapName(){
        return map;
    }

    public Point position(){
        return position;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Location))
            return false;
        Location location = (Location) other;
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
