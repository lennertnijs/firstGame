package com.mygdx.game.Graph;

import java.util.*;

public class Graph {
    private final HashMap<Vertex, ArrayList<Vertex>> graph;

    public Graph(){
        graph = new HashMap<>();
    }

    public void addVertex(Vertex vertex, ArrayList<Vertex> neighbors){

        graph.put(vertex, neighbors);
    }


    public ArrayList<Vertex> getPath(Vertex start, Vertex goal){
        if(!graph.containsKey(goal) || !graph.containsKey(start)){
            return null;
        }
        LinkedList<ArrayList<Vertex>> queue = new LinkedList<>();
        queue.add(new ArrayList<>(Arrays.asList(start)));
        while(!queue.isEmpty()){

            ArrayList<Vertex> currentPath = queue.pop();
            Vertex currentVertex = currentPath.get(currentPath.size()-1);
            ArrayList<Vertex> adjacentVertices = graph.get(currentVertex);

            for(int i = 0; i < adjacentVertices.size(); i++){
                Vertex newVertex = adjacentVertices.get(i);
                ArrayList<Vertex> newPath = new ArrayList<>(currentPath);
                if(newVertex.equals(goal)){
                    newPath.add(newVertex);
                    return newPath;
                }
                if(!currentPath.contains(newVertex)){
                    newPath.add(newVertex);
                    queue.addLast(newPath);
                }
            }
        }
        return null;
    }
}
