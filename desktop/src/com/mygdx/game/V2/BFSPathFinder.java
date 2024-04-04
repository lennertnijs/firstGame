package com.mygdx.game.V2;

import java.util.*;

public class BFSPathFinder implements PathFinderStrategy{


    public BFSPathFinder(){
    }

    @Override
    public List<MapPosition> findPath(MapPosition start, MapPosition goal, Map<MapPosition, List<MapPosition>> network) {
        Objects.requireNonNull(start, "Cannot find a path using BFS starting in null.");
        Objects.requireNonNull(goal, "Cannot find a path using BFS ending in null.");
        Objects.requireNonNull(network, "Cannot find a path using BFS over a null network.");
        if(network.containsKey(null) || network.containsValue(null))
            throw new NullPointerException("Cannot find a path using BFS because the network contains a null key or value.");
        LinkedList<List<MapPosition>> queue = new LinkedList<>();
        queue.add(Collections.singletonList(start));
        while(!queue.isEmpty()){
            List<MapPosition> currentPath = queue.pop();
            MapPosition currentMapPosition = currentPath.get(currentPath.size() - 1);
            if(!network.containsKey(currentMapPosition))
                throw new NoSuchElementException("No mapping for the given MapPosition was found in the network.");
            for(MapPosition mapPosition : network.get(currentMapPosition)){
                List<MapPosition> clonedPath = new ArrayList<>(currentPath);
                boolean visited = clonedPath.contains(mapPosition);
                if(!visited){
                    clonedPath.add(mapPosition);
                    if(mapPosition.equals(goal))
                        return clonedPath;
                    else
                        queue.addLast(clonedPath);
                }
            }
        }
        throw new NoSuchElementException("No path was found to the goal.");
    }
}
