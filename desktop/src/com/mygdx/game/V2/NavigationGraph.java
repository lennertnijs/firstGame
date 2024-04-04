package com.mygdx.game.V2;

import java.util.*;

public final class NavigationGraph {

    private final Graph<MapPosition> graph;

    private NavigationGraph(Graph<MapPosition> graph){
        this.graph = graph;
    }

    public static NavigationGraph create(Graph<MapPosition> graph){
        Objects.requireNonNull(graph, "Cannot create a MovementNetwork from null.");
        return new NavigationGraph(graph);
    }

    public Graph<MapPosition> getGraph(){
        return graph;
    }

    public Route findPath(MapPosition start, MapPosition goal, PathFinderStrategy<MapPosition> strategy){
        return Route.create(strategy.findPath(start, goal, graph));
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
