package com.mygdx.game.V2;

import java.util.*;

public final class NavigationGraph {

    private final Graph<Location> graph;
    private final PathFinderStrategy<Location> strategy;

    private NavigationGraph(Graph<Location> graph,  PathFinderStrategy<Location> strategy){
        this.graph = graph;
        this.strategy = strategy;
    }

    public static NavigationGraph create(Graph<Location> graph, PathFinderStrategy<Location> strategy){
        Objects.requireNonNull(graph, "Cannot create a NavigationGraph with a null Graph.");
        Objects.requireNonNull(strategy, "Cannot create a NavigationGraph with a null strategy.");
        return new NavigationGraph(graph, strategy);
    }

    public Graph<Location> getGraph(){
        return graph;
    }

    public List<Location> findPath(Location start, Location goal){
        return strategy.findPath(start, goal, graph);
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
