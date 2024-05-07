package com.mygdx.game.Navigation;

import java.util.List;
import java.util.NoSuchElementException;

public interface PathFinderStrategy<T> {

    /**
     * Finds a path between the start and goal objects.
     * @param start The start object. Cannot be null. Has to be a vertex in the {@link IGraph}.
     * @param goal The goal object. Cannot be null. Has to be a vertex in the {@link IGraph}.
     *
     * @return The path, from start to finish.
     * @throws NoSuchElementException If no path was found.
     * @throws NoSuchElementException If the start or goal object is not a vertex in the {@link IGraph}.
     * @throws NullPointerException If the start or goal object is null.
     */
    List<T> findPath(T start, T goal);
}
