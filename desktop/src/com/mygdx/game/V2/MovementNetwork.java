package com.mygdx.game.V2;

import java.util.*;

public final class MovementNetwork{

    private final Map<MapPosition, List<MapPosition>> mapping;

    private MovementNetwork(Map<MapPosition, List<MapPosition>> mapping){
        this.mapping = mapping;
    }

    public static MovementNetwork create(Map<MapPosition, List<MapPosition>> mapping){
        Objects.requireNonNull(mapping, "Cannot create a MovementNetwork from null.");
        if(mapping.containsKey(null) || mapping.containsValue(null))
            throw new NullPointerException("Cannot create a MovementNetwork with a null key or value.");
        return new MovementNetwork(mapping);
    }

    public Map<MapPosition, List<MapPosition>> getMapping(){
        return mapping;
    }

    public List<MapPosition> findPath(MapPosition start, MapPosition goal, PathFinderStrategy pathFinderStrategy){
        Objects.requireNonNull(start, "Cannot find a path starting in null.");
        Objects.requireNonNull(goal, "Cannot find a path ending in null.");
        Objects.requireNonNull(pathFinderStrategy, "Cannot use a null path finding strategy.");
        return pathFinderStrategy.findPath(start, goal, mapping);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof MovementNetwork))
            return false;
        MovementNetwork network = (MovementNetwork) other;
        return mapping.equals(network.mapping);
    }

    @Override
    public int hashCode(){
        return mapping.hashCode();
    }

    @Override
    public String toString(){
        return String.format("MovementNetwork[Mappings=%s]", mapping);
    }
}
