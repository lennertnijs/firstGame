package com.mygdx.game.V2.Generic;

import java.util.*;

public final class Graph<T> implements IGraph<T>{

    private Map<T, List<T>> adjacencyMap;

    public Graph(){
        this.adjacencyMap = new HashMap<>();
    }

    public Graph(Map<T, List<T>> adjacencyMap){
        Objects.requireNonNull(adjacencyMap, "Adjacency map is null.");
        for(T key : adjacencyMap.keySet())
            Objects.requireNonNull(key, "Adjacency map contains a null key.");
        for(List<T> value : adjacencyMap.values()) {
            Objects.requireNonNull(value, "Adjacency map contains a null value list.");
            if(value.contains(null))
                throw new NullPointerException("Adjacency map value list contains a null.");
            for(T edge : value){
                if(!adjacencyMap.containsKey(edge))
                    throw new NoSuchElementException();
            }
        }


    }

    public void addVertex(T vertex){
        Objects.requireNonNull(vertex, "Cannot add a null Vertex to the Graph.");
        adjacencyMap.put(vertex, new ArrayList<>());
    }

    public void addVertices(List<T> vertices){
        Objects.requireNonNull(vertices, "Cannot add vertices from null.");
        if(vertices.contains(null))
            throw new NullPointerException("Cannot add a null Vertex to the Graph.");
        for(T vertex : vertices)
            adjacencyMap.put(vertex, new ArrayList<>());
    }

    public void addEdge(T start, T end){
        Objects.requireNonNull(start, "Cannot add an Edge from a null Vertex.");
        Objects.requireNonNull(end, "Cannot add an Edge to a null Vertex.");
        if(!adjacencyMap.containsKey(start))
            throw new NoSuchElementException("Cannot add the Edge because the starting Vertex does not exist in the Graph.");
        if(!adjacencyMap.containsKey(end))
            throw new NoSuchElementException("Cannot add the Edge because the end Vertex does not exist in the Graph.");
        List<T> startEdges = adjacencyMap.get(start);
        startEdges.add(end);
        adjacencyMap.put(start, startEdges);
        List<T> endEdges = adjacencyMap.get(end);
        endEdges.add(start);
        adjacencyMap.put(end, endEdges);
    }

    public void addEdges(T start, List<T> ends){
        Objects.requireNonNull(start, "Cannot add an Edge from a null Vertex.");
        Objects.requireNonNull(ends, "Cannot add Edges from null.");
        if(ends.contains(null))
            throw new NullPointerException("Cannot add an Edge to a null Vertex.");
        if(!adjacencyMap.containsKey(start))
            throw new NoSuchElementException("Cannot add the Edge because the starting Vertex does not exist in the Graph.");
        for(T end : ends){
            if(!adjacencyMap.containsKey(end))
                throw new NoSuchElementException("Cannot add the Edge because the end Vertex does not exist in the Graph.");
        }
        List<T> startEdges = adjacencyMap.get(start);
        startEdges.addAll(ends);
        adjacencyMap.put(start, startEdges);
        for(T end : ends){
            List<T> endEdges = adjacencyMap.get(end);
            endEdges.add(start);
            adjacencyMap.put(end, endEdges);
        }
    }

    public void removeVertex(T vertex){
        adjacencyMap.remove(vertex);
    }

    public void removeEdge(T start, T end){
        adjacencyMap.get(start).remove(end);
        adjacencyMap.get(end).remove(start);
    }

    public Set<T> getVertices(){
        return adjacencyMap.keySet();
    }

    public int getDegree(T vertex){
        Objects.requireNonNull(vertex, "Vertex is null.");
        if(!adjacencyMap.containsKey(vertex))
            throw new NoSuchElementException("Vertex not found.");
        return adjacencyMap.get(vertex).size();
    }

    public List<T> getNeighbors(T vertex){
        Objects.requireNonNull(vertex, "Cannot fetch the neighbors of null.");
        return adjacencyMap.get(vertex);
    }

    public boolean hasVertex(T vertex){
        return adjacencyMap.containsKey(vertex);
    }

    public boolean hasEdge(T start, T end){
        return adjacencyMap.get(start).contains(end);
    }

    public boolean isEmpty(){
        return adjacencyMap.isEmpty();
    }

    public int vertexCount(){
        return adjacencyMap.keySet().size();
    }

    public int edgeCount(){
        int edgeCount = 0;
        for(T vertex : adjacencyMap.keySet()){
            edgeCount += adjacencyMap.get(vertex).size();
        }
        return edgeCount;
    }

    public void clear(){
        adjacencyMap = new HashMap<>();
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Graph<?>))
            return false;
        Graph<?> graph = (Graph<?>) other;
        return adjacencyMap.equals(graph.adjacencyMap);
    }

    @Override
    public int hashCode(){
        return adjacencyMap.hashCode();
    }

    @Override
    public String toString(){
        return String.format("Graph[adjacencyMap=%s]", adjacencyMap);
    }
}
