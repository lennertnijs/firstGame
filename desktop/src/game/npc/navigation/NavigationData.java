package game.npc.navigation;

import game.npc.navigation.graph.IGraph;

import java.util.*;

/**
 * Represents all the data surrounding movement & navigation.
 */
public final class NavigationData{

    private final IGraph<Location> graph;
    /**
     * The path finding strategy. Includes the graph.
     */
    private final PathFinderStrategy<Location> strategy;

    /**
     * Creates a new {@link NavigationData}.
     * @param strategy The {@link PathFinderStrategy}. Cannot be null.
     */
    public NavigationData(IGraph<Location> graph, PathFinderStrategy<Location> strategy){
        Objects.requireNonNull(strategy, "Path finding strategy is null.");
        this.strategy = strategy;
        this.graph = graph;
    }

    public List<Location> generateRoute(Location start, Location end){
        return strategy.findPath(start, end, graph);
    }

    @Override
    public String toString(){
        return String.format("NavigationData[strategy=%s]", strategy.getClass());
    }
}
