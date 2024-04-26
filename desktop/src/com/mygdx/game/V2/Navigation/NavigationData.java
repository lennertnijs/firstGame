package com.mygdx.game.V2.Navigation;

import com.mygdx.game.V2.Util.Location;
import com.mygdx.game.V2.Util.Point;

import java.util.*;

/**
 * Represents all the data surrounding movement & navigation.
 */
public final class NavigationData implements INavigationData{

    /**
     * The current route (if they're moving).
     */
    private Route currentRoute;
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
        this.currentRoute = new Route(new ArrayList<>());
    }

    /**
     * Creates a new {@link NavigationData}.
     * @param strategy The {@link PathFinderStrategy}. Cannot be null.
     * @param route The current {@link Route}. Cannot be null.
     */
    public NavigationData(PathFinderStrategy<Location> strategy, Route route){
        this.strategy = strategy;
        this.currentRoute = new Route(route.getLocations());
    }

    /**
     * {@inheritDoc}
     */
    public void calculateAndStoreRoute(Location start, Location goal){
        Objects.requireNonNull(start, "Start location is null.");
        Objects.requireNonNull(goal, "Goal location is null.");
        this.currentRoute = new Route(strategy.findPath(start, goal));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isMoving(){
        return currentRoute.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    public Location nextLocation(Location current, int speed, int delta){
        if(currentRoute.isEmpty())
            throw new IllegalStateException("No more movement happening.");
        if(speed <= 0 || delta <= 0)
            throw new IllegalArgumentException("Speed/delta is negative or 0.");
        Location next = currentRoute.getNext();
        int movement = speed * delta;
        int nextX = calculateNextX(current.position().x(), next.position().x(), movement);
        int nextY = calculateNextY(current.position().x(), next.position().x(), movement);
        Point nextPosition = new Point(nextX, nextY);
        boolean arrivedAtNextInRoute = nextPosition.equals(next.position());
        String nextMap = current.mapName();
        if(arrivedAtNextInRoute){
            nextMap = next.mapName();
            currentRoute.removeNext();
        }
        return new Location(nextMap, nextPosition);
    }

    /**
     * Calculates the next X value.
     * @param oldX The old X.
     * @param goalX The goal X.
     * @param movement The movement.
     *
     * @return The next X value.
     */
    private int calculateNextX(int oldX, int goalX, int movement){
        return oldX < goalX ? Math.min(oldX + movement, goalX) : Math.max(oldX - movement, goalX);
    }

    /**
     * Calculates the next Y value.
     * @param oldY The old Y.
     * @param goalY The goal Y.
     * @param movement The movement.
     *
     * @return The next Y value.
     */
    private int calculateNextY(int oldY, int goalY, int movement){
        return oldY < goalY ? Math.min(oldY + movement, goalY) : Math.max(oldY - movement, goalY);
    }

    /**
     * @return The string representation of this {@link NavigationData}.
     */
    @Override
    public String toString(){
        return String.format("NavigationData[currentRoute=%s, strategy=%s]", currentRoute, strategy);
    }
}
