package com.mygdx.game.V2.Util;

import java.util.Objects;

/**
 * Represents a location in the game.
 * A location means both a map & a position.
 * IMMUTABLE
 */
public final class Location {

    /**
     * The name of the map.
     */
    private final String mapName;
    /**
     * The position.
     */
    private final Point position;

    /**
     * Creates a new immutable {@link Location}.
     * @param mapName The map name. Cannot be null.
     * @param position The position. Cannot be null.
     */
    public Location(String mapName, Point position){
        Objects.requireNonNull(mapName, "Map name is null.");
        Objects.requireNonNull(position, "Point is null.");
        this.mapName = mapName;
        this.position = position;
    }

    /**
     * @return The map name.
     */
    public String mapName(){
        return mapName;
    }

    /**
     * @return The position.
     */
    public Point position(){
        return position;
    }

    public int x(){
        return position.x();
    }

    public int y(){
        return position.y();
    }

    /**
     * Compares this {@link Location} to the given object and returns true if they're equal. Returns false otherwise.
     * Two {@link Location}s are equal if they have the same map name & position.
     *
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Location))
            return false;
        Location location = (Location) other;
        return mapName.equals(location.mapName) && position.equals(location.position);
    }

    /**
     * @return The hash code of this {@link Location}.
     */
    @Override
    public int hashCode(){
        int result = mapName.hashCode();
        result = result * 31 + position.hashCode();
        return result;
    }

    /**
     * @return The string representation of this {@link Location}.
     */
    @Override
    public String toString(){
        return String.format("Location[mapName=%s, position=%s]", mapName, position);
    }
}
