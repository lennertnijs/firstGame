package com.mygdx.game.V2.WeekSchedule;

import com.mygdx.game.V2.Util.Point;

import java.util.Objects;

public final class Location {

    private final String mapName;
    private final Point point;


    private Location(String mapName, Point point){
        this.mapName = mapName;
        this.point = point;
    }

    public static Location create(String mapName, Point point){
        Objects.requireNonNull(mapName, "Cannot create a MapPosition with a null map name.");
        Objects.requireNonNull(point, "Cannot create a MapPosition from a null Position.");
        return new Location(mapName, point);
    }

    public String getMapName(){
        return mapName;
    }

    public Point getPosition(){
        return point;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Location)){
            return false;
        }
        Location location = (Location) other;
        return mapName.equals(location.mapName) &&
                point.equals(location.point);
    }

    @Override
    public int hashCode(){
        int result = mapName.hashCode();
        result = result * 31 + point.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Location[mapName=%s, %s]", mapName, point);
    }
}
