package com.mygdx.game.V2.Navigation;

import com.mygdx.game.V2.Util.Location;

import java.util.*;

/**
 * Represents a route over which one can traverse.
 */
public final class Route {

    /**
     * The locations.
     */
    private final List<Location> locations;

    /**
     * Creates a new empty {@link Route}.
     */
    public Route(){
        locations = new LinkedList<>();
    }

    /**
     * Creates a new {@link Route} with the given {@link Location}s.
     * @param locations The list of {@link Location}s. Cannot be null. Cannot contain null.
     */
    public Route(List<Location> locations){
        Objects.requireNonNull(locations, "List is null.");
        if(locations.contains(null))
            throw new NullPointerException("List contains null.");
        this.locations = new LinkedList<>(locations);
    }

    /**
     * @return A copy of the list of {@link Location}s in this {@link Route}.
     */
    public List<Location> getLocations(){
        return new LinkedList<>(locations);
    }

    /**
     * @return True if the route is empty. False otherwise.
     */
    public boolean isEmpty(){
        return locations.size() == 0;
    }

    /**
     * Appends the given {@link Location}s to the end of the {@link Route}.
     * @param locations The new {@link Location}s. Cannot be null. Cannot contain null.
     */
    public void add(List<Location> locations){
        Objects.requireNonNull(locations, "List is null.");
        if(locations.contains(null))
            throw new NullPointerException("List contains null.");
        this.locations.addAll(locations);
    }

    /**
     * Returns the next {@link Location} in this route, if it exists.
     *
     * @return The next {@link Location}.
     * @throws NoSuchElementException If the route was empty.
     */
    public Location peek(){
        if(locations.size() == 0)
            throw new NoSuchElementException("Route is empty.");
        return locations.get(0);
    }

    /**
     * Removes and returns the next {@link Location} from this route, if it exists.
     *
     * @return The next {@link Location}.
     * @throws NoSuchElementException If the route was empty.
     */
    public Location poll(){
        if(locations.size() == 0)
            throw new NoSuchElementException("Route is empty.");
        return locations.remove(0);
    }

    /**
     * Wipes the entire route, effectively emptying it out.
     */
    public void wipe(){
        Iterator<Location> it = locations.listIterator();
        while(it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    /**
     * Compares this {@link Route} to the given object and returns true if they're equal. Returns false otherwise.
     * Two {@link Route}s are equal if they hold the same {@link Location}s in the same order.
     *
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Route))
            return false;
        Route route = (Route) other;
        return locations.equals(route.locations);
    }

    /**
     * @return The hash code of this {@link Route}.
     */
    @Override
    public int hashCode(){
        return locations.hashCode();
    }

    /**
     * @return The string representation of this {@link Route}.
     */
    @Override
    public String toString(){
        return String.format("Route[locations=%s]", locations);
    }


}
