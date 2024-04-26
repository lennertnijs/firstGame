package com.mygdx.game.V2.Navigation;

import java.util.*;

/**
 * Represents a pathfinder using Bread-first search.
 */
public class BFSPathFinder<T> implements PathFinderStrategy<T> {

    /**
     * The graph.
     */
    private final IGraph<T> graph;

    /**
     * Creates a new, semi-immutable {@link BFSPathFinder}.
     * Note: it's semi-immutable because it copies the {@link IGraph}, but this means it relies on the immutability
     * of the objects in the {@link IGraph}.
     * @param graph The {@link IGraph}. Cannot be null.
     */
    public BFSPathFinder(IGraph<T> graph){
        Objects.requireNonNull(graph, "Graph is null.");
        this.graph = graph.copy();
    }

    /**
     * {@inheritDoc}
     * Finds one of the shortest paths (in vertices).
     */
    @Override
    public List<T> findPath(T start, T goal) {
        Objects.requireNonNull(start, "Start is null.");
        Objects.requireNonNull(goal, "Goal is null.");
        if(!graph.hasVertex(start) || !graph.hasVertex(goal))
            throw new NoSuchElementException("Start or goal is not part of the Graph.");
        return BFS(start, goal);
    }

    /**
     * Finds one of the shortest paths (in vertices) between start and goal, in the graph.
     * @param start The start object. Cannot be null.
     * @param goal The end object. Cannot be null.
     *
     * @return The path.
     * @throws NoSuchElementException If no path was found.
     */
    private List<T> BFS(T start, T goal){
        Queue<List<T>> queue = new LinkedList<>();
        queue.add(new LinkedList<>(Collections.singletonList(start)));

        while(!queue.isEmpty()){
            List<T> currentPath = queue.poll();
            T current = currentPath.get(currentPath.size() - 1);

            if(current.equals(goal)) return currentPath;

            for(T neighbor : graph.getNeighbors(current)){
                if(currentPath.contains(neighbor)) continue;

                List<T> path = new LinkedList<>(currentPath);
                path.add(neighbor);
                queue.add(path);
            }
        }
        throw new NoSuchElementException("BFS could not find a path.");
    }
}
