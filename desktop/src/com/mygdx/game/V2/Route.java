package com.mygdx.game.V2;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class Route {

    private final List<Location> locations;

    private Route(List<Location> locations){
        this.locations = locations;
    }

    public static Route create(List<Location> locations){
        Objects.requireNonNull(locations, "Cannot create a Route from null.");
        if(locations.contains(null))
            throw new NullPointerException("Cannot create a Route with a null Position.");
        return new Route(locations);
    }

    public List<Location> getMapPositions(){
        return locations;
    }

    public int getLength(){
        return locations.size();
    }

    public boolean isEmpty(){
        return locations.isEmpty();
    }

    public Location getNext(){
        if(locations.isEmpty())
            throw new NoSuchElementException("No more Positions are left in the Route.");
        return locations.get(0);
    }


    public Location getAndRemoveNext(){
        if(locations.isEmpty())
            throw new NoSuchElementException("No more Positions are left in the Route.");
        Location nextLocation = locations.get(0);
        locations.remove(0);
        return nextLocation;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Route)){
            return false;
        }
        Route route = (Route) other;
        return locations.equals(route.locations);
    }

    @Override
    public int hashCode(){
        return locations.hashCode();
    }

    @Override
    public String toString(){
        return String.format("Route[positions=%s]", locations.toString());
    }
}
