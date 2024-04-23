package com.mygdx.game.V2;

import com.mygdx.game.V2.Graph.IGraph;
import com.mygdx.game.V2.Util.Location;

import java.util.*;

public final class NavigationGraph {

    private final IGraph<Location> graph;
    private final PathFinderStrategy<Location> strategy;

    private NavigationGraph(IGraph<Location> graph, PathFinderStrategy<Location> strategy){
        this.graph = graph;
        this.strategy = strategy;
    }

    public static NavigationGraph create(IGraph<Location> graph, PathFinderStrategy<Location> strategy){
        Objects.requireNonNull(graph, "Cannot create a NavigationGraph with a null Graph.");
        Objects.requireNonNull(strategy, "Cannot create a NavigationGraph with a null strategy.");
        return new NavigationGraph(graph, strategy);
    }

    public IGraph<Location> getGraph(){
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
