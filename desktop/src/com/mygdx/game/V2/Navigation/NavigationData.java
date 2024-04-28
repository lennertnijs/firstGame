package com.mygdx.game.V2.Navigation;

import com.mygdx.game.V2.Util.Location;
import com.mygdx.game.V2.Util.Point;
import com.mygdx.game.V2.Util.Vector;

import java.util.*;

/**
 * Represents all the data surrounding movement & navigation.
 */
public final class NavigationData implements INavigationData{

    /**
     * The current route (if they're moving).
     */
    private final Route route;
    /**
     * The path finding strategy. Includes the graph.
     */
    private final PathFinderStrategy<Location> strategy;

    /**
     * Creates a new {@link NavigationData}.
     * @param strategy The {@link PathFinderStrategy}. Cannot be null.
     */
    public NavigationData(PathFinderStrategy<Location> strategy){
        this.strategy = strategy;
        this.route = new Route();
    }

    /**
     * Creates a new {@link NavigationData}.
     * @param strategy The {@link PathFinderStrategy}. Cannot be null.
     * @param route The current list of locations. Cannot be null. Cannot contain null.
     */
    public NavigationData(PathFinderStrategy<Location> strategy, List<Location> route){
        this.strategy = strategy;
        this.route = new Route(route);
    }

    /**
     * {@inheritDoc}
     */
    public void calculateAndStoreRoute(Location start, Location goal){
        Objects.requireNonNull(start, "Start location is null.");
        Objects.requireNonNull(goal, "Goal location is null.");
        List<Location> route = strategy.findPath(start, goal);
        this.route.add(route);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isMoving(){
        return !route.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    public Location calculateNextLocation(Location current, int movement){
        Objects.requireNonNull(current, "Current location is null.");
        if(movement <= 0)
            throw new IllegalArgumentException("Movement <= 0.");

        Location next = route.peek();
        Vector vectorToNext = new Vector(next.x() - current.x(),next.y() - current.y());
        if(vectorToNext.size() <= movement){
            route.poll();
            if(route.isEmpty())
                return next;
            int remainingMovement = movement - vectorToNext.size();
            return calculateNextLocation(next, remainingMovement);
        }
        Vector scaled = vectorToNext.scaleToSize(movement);
        Point nextPosition = new Point(current.x() + scaled.x(), current.y() + scaled.y());
        return new Location(current.mapName(), nextPosition);
    }


    /**
     * @return The string representation of this {@link NavigationData}.
     */
    @Override
    public String toString(){
        return String.format("NavigationData[currentRoute=%s, strategy=%s]", route, strategy);
    }
}
