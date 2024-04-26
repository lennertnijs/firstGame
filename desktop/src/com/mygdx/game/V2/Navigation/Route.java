package com.mygdx.game.V2.Navigation;

import com.mygdx.game.V2.Util.Location;

import java.util.*;

public final class Route {

    private final Queue<Location> route;

    public Route(List<Location> locations){
        Objects.requireNonNull(locations, "List is null.");
        if(locations.contains(null))
            throw new NullPointerException("List contains null Location.");
        this.route = new LinkedList<>(locations);
    }

    public List<Location> getLocations(){
        return new ArrayList<>(route);
    }

    public int getLength(){
        return route.size();
    }

    public boolean isEmpty(){
        return route.isEmpty();
    }

    public Location getNext(){
        if(route.isEmpty())
            throw new NoSuchElementException("No more Positions are left in the Route.");
        return route.peek();
    }


    public Location getAndRemoveNext(){
        if(route.isEmpty())
            throw new NoSuchElementException("No more Positions are left in the Route.");
        return route.poll();
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Route))
            return false;
        Route route = (Route) other;
        return this.route.equals(route.route);
    }

    @Override
    public int hashCode(){
        return route.hashCode();
    }

    @Override
    public String toString(){
        return String.format("Route[positions=%s]", route);
    }
}
