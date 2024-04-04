package com.mygdx.game.V2;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class Route {

    private final List<MapPosition> mapPositions;

    private Route(List<MapPosition> mapPositions){
        this.mapPositions = mapPositions;
    }

    public static Route create(List<MapPosition> mapPositions){
        Objects.requireNonNull(mapPositions, "Cannot create a Route from null.");
        if(mapPositions.contains(null))
            throw new NullPointerException("Cannot create a Route with a null Position.");
        return new Route(mapPositions);
    }

    public List<MapPosition> getMapPositions(){
        return mapPositions;
    }

    public int getLength(){
        return mapPositions.size();
    }

    public boolean isEmpty(){
        return mapPositions.isEmpty();
    }

    public MapPosition getNext(){
        if(mapPositions.isEmpty())
            throw new NoSuchElementException("No more Positions are left in the Route.");
        return mapPositions.get(0);
    }


    public MapPosition getAndRemoveNext(){
        if(mapPositions.isEmpty())
            throw new NoSuchElementException("No more Positions are left in the Route.");
        MapPosition nextMapPosition = mapPositions.get(0);
        mapPositions.remove(0);
        return nextMapPosition;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Route)){
            return false;
        }
        Route route = (Route) other;
        return mapPositions.equals(route.mapPositions);
    }

    @Override
    public int hashCode(){
        return mapPositions.hashCode();
    }

    @Override
    public String toString(){
        return String.format("Route[positions=%s]", mapPositions.toString());
    }
}
