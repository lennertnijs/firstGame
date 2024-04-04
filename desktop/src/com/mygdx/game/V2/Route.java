package com.mygdx.game.V2;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class Route {

    private final List<Position> positions;

    private Route(List<Position> positions){
        this.positions = positions;
    }

    public static Route create(List<Position> positions){
        Objects.requireNonNull(positions, "Cannot create a Route from null.");
        if(positions.contains(null))
            throw new NullPointerException("Cannot create a Route with a null Position.");
        return new Route(positions);
    }

    public List<Position> getPositions(){
        return positions;
    }

    public int getLength(){
        return positions.size();
    }

    public boolean isEmpty(){
        return positions.isEmpty();
    }

    public Position getNext(){
        if(positions.isEmpty())
            throw new NoSuchElementException("No more Positions are left in the Route.");
        return positions.get(0);
    }


    public Position getAndRemoveNext(){
        if(positions.isEmpty())
            throw new NoSuchElementException("No more Positions are left in the Route.");
        Position nextPosition = positions.get(0);
        positions.remove(0);
        return nextPosition;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Route)){
            return false;
        }
        Route route = (Route) other;
        return positions.equals(route.positions);
    }

    @Override
    public int hashCode(){
        return positions.hashCode();
    }

    @Override
    public String toString(){
        return String.format("Route[positions=%s]", positions.toString());
    }
}
