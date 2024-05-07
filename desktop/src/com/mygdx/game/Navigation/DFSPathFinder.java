package com.mygdx.game.Navigation;

import java.util.*;

/**
 * Represents a pathfinder using Depth-first search.
 */
public final class DFSPathFinder<T> implements PathFinderStrategy<T>{

    /**
     * The graph.
     */
    private final IGraph<T> graph;

    /**
     * Creates a new, semi-immutable {@link DFSPathFinder}.
     * Note: it's semi-immutable because it copies the {@link IGraph}, but this means it relies on the immutability
     * of the objects in the {@link IGraph}.
     * @param graph The {@link IGraph}. Cannot be null.
     */
    public DFSPathFinder(IGraph<T> graph){
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
        List<T> solution = DFS(start, goal, new ArrayList<>(Collections.singletonList(start)), new ArrayList<>());
        if(solution.isEmpty())
            throw new NoSuchElementException("No path was found between start and goal.");
        return solution;
    }

    /**
     * The recursive method for Depth-first search.
     * Stores the current path, and the current best solution.
     */
    private List<T> DFS(T current, T goal, List<T> path, List<T> solution){
        if(current.equals(goal) && (solution.isEmpty() || path.size() < solution.size()))
            solution = new ArrayList<>(path);
        for(T neighbor : graph.getNeighbors(current)){
            if(path.contains(neighbor)) continue;
            path.add(neighbor);
            solution = DFS(neighbor, goal, path, solution);
            path.remove(path.size() - 1);
        }
        return solution;
    }
}
