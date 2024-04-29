package com.mygdx.game.V2.Navigation;

import com.mygdx.game.V2.Util.Location;

import java.util.NoSuchElementException;

/**
 * Represents all the data surrounding movement & navigation.
 */
public interface INavigationData {


    /**
     * @return True if the current route is not empty, meaning they're moving. False otherwise.
     */
    boolean isMoving();

    /**
     * Calculates and internally stores a route from the start to the end {@link Location}.
     * @param start The start {@link Location}. Cannot be null. Must be in the graph.
     * @param goal The goal {@link Location}. Cannot be null. Must be in the graph.
     *
     * @throws NoSuchElementException If the start or goal object is not in the graph, or no path between them was found.
     * @throws NullPointerException If the start or goal object is null.
     */
    void calculateAndStoreRoute(Location start, Location goal);

    /**
     * Calculates and returns the next {@link Location} after applying movement.
     * @param current The current {@link Location}. Cannot be null.
     * @param movement The movement speed. Cannot be negative or 0.
     *
     * @return The next {@link Location}.
     * @throws IllegalArgumentException If the speed or delta is negative or 0.
     * @throws NullPointerException If the current {@link Location} is null.
     */
    Location calculateNextLocation(Location current, int movement);
}
