package com.mygdx.game.NPC;

import com.mygdx.game.Entity.Position2D;

import java.util.*;

public class MovementGraph {
    private final HashMap<Position2D, ArrayList<Position2D>> movementGraph;

    public MovementGraph(Builder builder){
        this.movementGraph = builder.movementGraph;
    }


    /**
     * BFS algorithm to find path in graph
     */
    public ArrayList<Position2D> findPath(Position2D start, Position2D goal){
        if(!movementGraph.containsKey(goal)){
            throw new IllegalArgumentException("The starting position does not exist in the movement graph.");
        }
        if(!movementGraph.containsKey(start)){
            throw new IllegalArgumentException("The goal position does not exist in the movement graph.");
        }
        LinkedList<ArrayList<Position2D>> queue = new LinkedList<>();
        queue.add(new ArrayList<>(Collections.singletonList(start)));

        while(!queue.isEmpty()){
            ArrayList<Position2D> currentPath = queue.pop();
            int lastIndex = currentPath.size()-1;
            Position2D currentPosition = currentPath.get(lastIndex);
            if(!movementGraph.containsKey(currentPosition)){
                throw new IllegalArgumentException("The current position does not exist in the movement graph");
            }
            ArrayList<Position2D> adjacentVertices = movementGraph.get(currentPosition);

            for (Position2D nextPosition : adjacentVertices) {
                ArrayList<Position2D> newPath = new ArrayList<>(currentPath);

                if (nextPosition.equals(goal)) {
                    newPath.add(nextPosition);
                    return newPath;
                }
                if (!currentPath.contains(nextPosition)) {
                    newPath.add(nextPosition);
                    queue.addLast(newPath);
                }
            }
        }

        throw new IllegalArgumentException("No path was found inside the movement graph");
    }




    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private HashMap<Position2D, ArrayList<Position2D>> movementGraph = new HashMap<>();

        public Builder movementGraph(HashMap<Position2D, ArrayList<Position2D>> movementGraph){
            this.movementGraph = movementGraph;
            return this;
        }

        public MovementGraph build(){
            Objects.requireNonNull(movementGraph, "The movement graph must not be null");
            for(Position2D node: movementGraph.keySet()){
                Objects.requireNonNull(node, "Key values in the movement graph must not be null");
                Objects.requireNonNull(movementGraph.get(node), "Values in the movement graph must not be null");
                for(Position2D connectedNode : movementGraph.get(node)){
                    Objects.requireNonNull(connectedNode, "Value lists in the movement graph must not contain null");
                }
            }
            return new MovementGraph(this);
        }
    }
}
