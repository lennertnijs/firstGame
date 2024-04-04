package com.mygdx.game.V2;

import java.util.*;

public class BFSPathFinder<T> implements PathFinderStrategy<T>{


    public BFSPathFinder(){
    }

    @Override
    public List<T> findPath(T start, T goal, Map<T, List<T>> network) {
        Objects.requireNonNull(start, "Cannot find a path using BFS starting in null.");
        Objects.requireNonNull(goal, "Cannot find a path using BFS ending in null.");
        Objects.requireNonNull(network, "Cannot find a path using BFS over a null network.");
        if(network.containsKey(null) || network.containsValue(null))
            throw new NullPointerException("Cannot find a path using BFS because the network contains a null key or value.");
        LinkedList<List<T>> queue = new LinkedList<>();
        queue.add(Collections.singletonList(start));
        while(!queue.isEmpty()){
            List<T> currentPath = queue.pop();
            T currentMapPosition = currentPath.get(currentPath.size() - 1);
            if(!network.containsKey(currentMapPosition))
                throw new NoSuchElementException("No mapping for the given MapPosition was found in the network.");
            for(T t : network.get(currentMapPosition)){
                List<T> clonedPath = new ArrayList<>(currentPath);
                boolean visited = clonedPath.contains(t);
                if(!visited){
                    clonedPath.add(t);
                    if(t.equals(goal))
                        return clonedPath;
                    else
                        queue.addLast(clonedPath);
                }
            }
        }
        throw new NoSuchElementException("No path was found to the goal.");
    }
}
