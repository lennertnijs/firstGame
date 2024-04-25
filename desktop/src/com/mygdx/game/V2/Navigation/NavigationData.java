package com.mygdx.game.V2.Navigation;

import com.mygdx.game.V2.Util.Location;

import java.util.*;

public final class NavigationData {

    private final IGraph<Location> graph;
    private final PathFinderStrategy<Location> strategy;
    private Route currentRoute;

    private NavigationData(IGraph<Location> graph, PathFinderStrategy<Location> strategy){
        this.graph = graph;
        this.strategy = strategy;
    }

    public static NavigationData create(IGraph<Location> graph, PathFinderStrategy<Location> strategy){
        Objects.requireNonNull(graph, "Cannot create a NavigationGraph with a null Navigation.");
        Objects.requireNonNull(strategy, "Cannot create a NavigationGraph with a null strategy.");
        return new NavigationData(graph, strategy);
    }

    public IGraph<Location> getGraph(){
        return graph;
    }

    public List<Location> findPath(Location start, Location goal){
        return strategy.findPath(start, goal, graph);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof NavigationData))
            return false;
        NavigationData network = (NavigationData) other;
        return graph.equals(network.graph);
    }

    @Override
    public int hashCode(){
        return graph.hashCode();
    }

    @Override
    public String toString(){
        return String.format("MovementNetwork[Navigation=%s]", graph);
    }
}
