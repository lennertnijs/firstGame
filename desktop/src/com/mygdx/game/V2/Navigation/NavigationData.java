package com.mygdx.game.V2.Navigation;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.V2.Util.Location;
import com.mygdx.game.V2.Util.Point;

import java.util.*;

public final class NavigationData {

    private final PathFinderStrategy<Location> strategy;
    private Route currentRoute;

    public NavigationData(PathFinderStrategy<Location> strategy){
        this.strategy = strategy;
        this.currentRoute = new Route(new ArrayList<>());
    }

    public NavigationData(PathFinderStrategy<Location> strategy, Route route){
        this.strategy = strategy;
        this.currentRoute = new Route(route.getLocations());
    }

    public void calculateRoute(Location start, Location end){
        this.currentRoute = new Route(strategy.findPath(start, end));
    }

    public boolean isMoving(){
        return currentRoute.isEmpty();
    }

    public Location nextLocation(Location start, int speed){
        if(currentRoute.isEmpty())
            return start;
        Location next = currentRoute.getNext();
        int movement = (int) (speed * Gdx.graphics.getDeltaTime());
        int newX = calculateNewX(start.position().x(), next.position().x(), movement);
        int newY = calculateNewY(start.position().x(), next.position().x(), movement);
        Point newPosition = new Point(newX, newY);
        String newMap = newPosition.equals(next.position()) ? next.mapName() : start.mapName();
        return new Location(newMap, newPosition);
    }

    private int calculateNewX(int oldX, int goalX, int movement){
        return oldX < goalX ? Math.min(oldX + movement, goalX) : Math.max(oldX - movement, goalX);
    }

    private int calculateNewY(int oldY, int goalY, int movement){
        return oldY < goalY ? Math.min(oldY + movement, goalY) : Math.max(oldY - movement, goalY);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof NavigationData))
            return false;
        NavigationData network = (NavigationData) other;
        return true;
    }

    @Override
    public int hashCode(){
        return 1;
    }

    @Override
    public String toString(){
        return String.format("MovementNetwork[Navigation=%s]", currentRoute);
    }
}
