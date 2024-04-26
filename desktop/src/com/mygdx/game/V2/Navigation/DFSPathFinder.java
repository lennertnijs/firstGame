package com.mygdx.game.V2.Navigation;

import java.util.*;

public final class DFSPathFinder<T> implements PathFinderStrategy<T>{

    private final IGraph<T> graph;
    public DFSPathFinder(IGraph<T> graph){
        this.graph = graph.copy();
    }
    @Override
    public List<T> findPath(T start, T goal) {
        Objects.requireNonNull(start, "Start is null.");
        Objects.requireNonNull(goal, "Goal is null.");
        if(!graph.hasVertex(start) || !graph.hasVertex(goal))
            throw new NoSuchElementException("Start or goal is not part of the Graph.");
        return DFS(start, goal, Collections.singletonList(start), new ArrayList<>());
    }

    private List<T> DFS(T current, T goal, List<T> path, List<T> solution){
        for(T neighbor : graph.getNeighbors(current)){
            if(path.contains(neighbor)) continue;
            path.add(neighbor);
            if(neighbor.equals(goal) && (solution.isEmpty() || path.size() < solution.size()))
                solution = new ArrayList<>(path);
            DFS(neighbor, goal, path, solution);
            path.remove(path.size() - 1);
        }
        return solution;
    }
}
