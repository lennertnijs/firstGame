package com.mygdx.game.V2.Navigation;

import java.util.*;

public class BFSPathFinder<T> implements PathFinderStrategy<T> {

    private final IGraph<T> graph;

    public BFSPathFinder(IGraph<T> graph){
        Objects.requireNonNull(graph, "Graph is null.");
        this.graph = graph.copy();
    }

    @Override
    public List<T> findPath(T start, T goal) {
        Objects.requireNonNull(start, "Start is null.");
        Objects.requireNonNull(goal, "Goal is null.");
        if(!graph.hasVertex(start) || !graph.hasVertex(goal))
            throw new NoSuchElementException("Start or goal is not part of the Graph.");
        return BFS(start, goal);
    }

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
