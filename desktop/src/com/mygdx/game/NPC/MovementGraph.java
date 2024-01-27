package com.mygdx.game.NPC;

import com.mygdx.game.Entity.Position;

import java.util.*;

public class MovementGraph {
    private final Map<Position, ArrayList<Position>> movementGraph;

    public MovementGraph(Builder builder){
        this.movementGraph = builder.movementGraph;
    }

    public Map<Position, ArrayList<Position>> getMovementGraph(){
        return this.movementGraph;
    }

    public List<Position> findPath(Position start, Position goal){
        Objects.requireNonNull(start, "The start position in the movement graph must not be null");
        Objects.requireNonNull(goal, "The goal position in the movement graph must not be null");
        if(!movementGraph.containsKey(start) || !movementGraph.containsKey(goal)){
            throw new IllegalArgumentException("The goal position does not exist in the movement graph.");
        }
        if(start.equals(goal)){
            return new ArrayList<>();
        }
        return breadFirstSearch(start, goal);
    }


    private List<Position> breadFirstSearch(Position start, Position goal){
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


    @Override
    public boolean equals(Object o){
        if( this == o){
            return true;
        }
        if(!(o instanceof MovementGraph)){
            return false;
        }
        MovementGraph graph = (MovementGraph) o;
        return movementGraph.equals(graph.movementGraph);
    }

    @Override
    public int hashCode(){
        return Objects.hash(movementGraph);
    }


    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Map<Position, ArrayList<Position>> movementGraph = new HashMap<>();

        public Builder movementGraph(Map<Position, ArrayList<Position>> movementGraph){
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
