package com.mygdx.game.NPC;

import com.mygdx.game.Entity.Position;

import java.util.*;

public class MovementGraph {
    private final HashMap<Position, ArrayList<Position>> movementGraph;

    public MovementGraph(Builder builder){
        this.movementGraph = builder.movementGraph;
    }


    /**
     * BFS algorithm to find path in graph
     */
    public ArrayList<Position> findPath(Position start, Position goal){
        if(!movementGraph.containsKey(start) || !movementGraph.containsKey(goal)){
            throw new IllegalArgumentException("The goal position does not exist in the movement graph.");
        }

        if(start.equals(goal)){
            return new ArrayList<>();
        }

        LinkedList<ArrayList<Position>> queue = new LinkedList<>();
        queue.add(new ArrayList<>(Collections.singletonList(start)));

        while(!queue.isEmpty()){
            ArrayList<Position> currentPath = queue.pop();
            Position currentPosition = currentPath.get(currentPath.size()-1);

            if(!movementGraph.containsKey(currentPosition)){
                throw new IllegalArgumentException("The current position does not exist in the movement graph");
            }
            ArrayList<Position> adjacentPositions = movementGraph.get(currentPosition);
            for (Position nextPosition : adjacentPositions) {
                ArrayList<Position> newPath = new ArrayList<>(currentPath);

                boolean alreadyVisited = currentPath.contains(nextPosition);
                if (!alreadyVisited) {
                    newPath.add(nextPosition);
                    queue.addLast(newPath);

                    boolean arrivedAtGoal = nextPosition.equals(goal);
                    if (arrivedAtGoal) {
                        return newPath;
                    }
                }
            }
        }
        throw new IllegalArgumentException("No path was found inside the movement graph");
    }




    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private HashMap<Position, ArrayList<Position>> movementGraph = new HashMap<>();

        public Builder movementGraph(HashMap<Position, ArrayList<Position>> movementGraph){
            this.movementGraph = movementGraph;
            return this;
        }

        public MovementGraph build(){
            Objects.requireNonNull(movementGraph, "The movement graph must not be null");
            for(Position node: movementGraph.keySet()){
                Objects.requireNonNull(node, "Key values in the movement graph must not be null");
                Objects.requireNonNull(movementGraph.get(node), "Values in the movement graph must not be null");
                for(Position connectedNode : movementGraph.get(node)){
                    Objects.requireNonNull(connectedNode, "Value lists in the movement graph must not contain null");
                }
            }
            return new MovementGraph(this);
        }
    }
}
