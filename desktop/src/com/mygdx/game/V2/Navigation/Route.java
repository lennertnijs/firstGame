package com.mygdx.game.V2.Navigation;

import com.mygdx.game.V2.Util.Location;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class Route {

    private final List<Location> locations;

    public Route(){
        locations = new LinkedList<>();
    }

    public Route(List<Location> locations){
        Objects.requireNonNull(locations, "List is null.");
        if(locations.contains(null))
            throw new NullPointerException("List contains null.");
        this.locations = new LinkedList<>(locations);
    }

    public List<Location> getLocations(){
        return new LinkedList<>(locations);
    }

    public void add(List<Location> locations){
        Objects.requireNonNull(locations, "List is null.");
        if(locations.contains(null))
            throw new NullPointerException("List contains null.");
        this.locations.addAll(locations);
    }

    public boolean isEmpty(){
        return locations.size() == 0;
    }

    public Location peek(){
        if(locations.size() == 0)
            throw new NoSuchElementException("Route is empty.");
        return locations.get(0);
    }

    public Location poll(){
        if(locations.size() == 0)
            throw new NoSuchElementException("Route is empty.");
        return locations.remove(0);
    }


}
