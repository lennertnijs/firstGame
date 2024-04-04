package com.mygdx.game.V2;

import java.util.Objects;

public final class Location {

    private final String mapName;
    private final Position position;


    private Location(String mapName, Position position){
        this.mapName = mapName;
        this.position = position;
    }

    public static Location create(String mapName, Position position){
        Objects.requireNonNull(mapName, "Cannot create a MapPosition with a null map name.");
        Objects.requireNonNull(position, "Cannot create a MapPosition from a null Position.");
        return new Location(mapName, position);
    }

    public String getMapName(){
        return mapName;
    }

    public Position getPosition(){
        return position;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Location)){
            return false;
        }
        Location location = (Location) other;
        return mapName.equals(location.mapName) &&
                position.equals(location.position);
    }

    @Override
    public int hashCode(){
        int result = mapName.hashCode();
        result = result * 31 + position.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Location[mapName=%s, %s]", mapName, position);
    }
}
