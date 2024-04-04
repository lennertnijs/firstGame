package com.mygdx.game.V2;

import java.util.Objects;

public final class MapPosition {

    private final String mapName;
    private final Position position;


    private MapPosition(String mapName, Position position){
        this.mapName = mapName;
        this.position = position;
    }

    public static MapPosition create(String mapName, Position position){
        Objects.requireNonNull(mapName, "Cannot create a MapPosition with a null map name.");
        Objects.requireNonNull(position, "Cannot create a MapPosition from a null Position.");
        return new MapPosition(mapName, position);
    }

    public String getMapName(){
        return mapName;
    }

    public Position getPosition(){
        return position;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof MapPosition)){
            return false;
        }
        MapPosition mapPosition = (MapPosition) other;
        return mapName.equals(mapPosition.mapName) &&
                position.equals(mapPosition.position);
    }

    @Override
    public int hashCode(){
        int result = mapName.hashCode();
        result = result * 31 + position.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("MapPosition[mapName=%s, %s]", mapName, position);
    }
}
