package com.mygdx.game.V2.Navigation;

import com.mygdx.game.V2.Util.Location;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class Route {

    private final List<Location> locations;

    public Route(List<Location> locations){
        this.locations = locations;
    }

    public List<Location> getLocations(){
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
