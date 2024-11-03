package com.mygdx.game.npc.navigation;

import com.mygdx.game.npc.navigation.graph.IGraph;

import java.util.*;

/**
 * Represents a pathfinder using Bread-first search.
 */
public class BFSPathFinder<T> implements PathFinderStrategy<T> {

    /**
     * Creates a new, semi-immutable {@link BFSPathFinder}.
     * Note: it's semi-immutable because it copies the {@link IGraph}, but this means it relies on the immutability
     * of the objects in the {@link IGraph}.
     */
    public BFSPathFinder(){
    }

    /**
     * {@inheritDoc}
     * Finds one of the shortest paths (in vertices).
     */
    @Override
    public List<T> findPath(T start, T goal, IGraph<T> graph) {
        Objects.requireNonNull(start, "Start is null.");
        Objects.requireNonNull(goal, "Goal is null.");
        if(!graph.hasVertex(start) || !graph.hasVertex(goal)) {
            throw new NoSuchElementException("Start or goal is not part of the Graph.");
        }
        Objects.requireNonNull(graph, "Graph is null.");
        return BFS(start, goal, graph);
    }

    private List<T> BFS(T start, T goal, IGraph<T> graph){
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
