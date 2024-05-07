package com.mygdx.game.Util;

import java.util.Objects;

public final class Location {

    private final String mapName;
    private final Point position;

    public Location(String mapName, Point position){
        Objects.requireNonNull(mapName, "Map name is null.");
        Objects.requireNonNull(position, "Point is null.");
        this.mapName = mapName;
        this.position = position;
    }

    public String mapName(){
        return mapName;
    }

    public Point position(){
        return position;
    }

    public int x(){
        return position.x();
    }

    public int y(){
        return position.y();
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Location))
            return false;
        Location location = (Location) other;
        return mapName.equals(location.mapName) && position.equals(location.position);
    }

    @Override
    public int hashCode(){
        int result = mapName.hashCode();
        result = result * 31 + position.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Location[mapName=%s, position=%s]", mapName, position);
    }
}
