package com.mygdx.game.V2;

import java.util.*;

public final class NavigationGraph {

    private final Graph<Location> graph;

    private NavigationGraph(Graph<Location> graph){
        this.graph = graph;
    }

    public static NavigationGraph create(Graph<Location> graph){
        Objects.requireNonNull(graph, "Cannot create a MovementNetwork from null.");
        return new NavigationGraph(graph);
    }

    public Graph<Location> getGraph(){
        return graph;
    }

    public Route findPath(Location start, Location goal, PathFinderStrategy<Location> strategy){
        List<Location> path = strategy.findPath(start, goal, graph);
        return Route.create(path);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof NavigationGraph))
            return false;
        NavigationGraph network = (NavigationGraph) other;
        return graph.equals(network.graph);
    }

    @Override
    public int hashCode(){
        return graph.hashCode();
    }

    @Override
    public String toString(){
        return String.format("MovementNetwork[Graph=%s]", graph);
    }
}
