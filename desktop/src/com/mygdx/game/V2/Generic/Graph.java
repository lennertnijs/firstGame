package com.mygdx.game.V2.Generic;

import java.util.*;

public final class Graph<T> implements IGraph<T>{

    private Map<T, List<T>> adjacencyList;

    public Graph(){
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex){
        Objects.requireNonNull(vertex, "Cannot add a null Vertex to the Graph.");
        adjacencyList.put(vertex, new ArrayList<>());
    }

    public void addVertices(List<T> vertices){
        Objects.requireNonNull(vertices, "Cannot add vertices from null.");
        if(vertices.contains(null))
            throw new NullPointerException("Cannot add a null Vertex to the Graph.");
        for(T vertex : vertices)
            adjacencyList.put(vertex, new ArrayList<>());
    }

    public void addEdge(T start, T end){
        Objects.requireNonNull(start, "Cannot add an Edge from a null Vertex.");
        Objects.requireNonNull(end, "Cannot add an Edge to a null Vertex.");
        if(!adjacencyList.containsKey(start))
            throw new NoSuchElementException("Cannot add the Edge because the starting Vertex does not exist in the Graph.");
        if(!adjacencyList.containsKey(end))
            throw new NoSuchElementException("Cannot add the Edge because the end Vertex does not exist in the Graph.");
        List<T> startEdges = adjacencyList.get(start);
        startEdges.add(end);
        adjacencyList.put(start, startEdges);
        List<T> endEdges = adjacencyList.get(end);
        endEdges.add(start);
        adjacencyList.put(end, endEdges);
    }

    public void addEdges(T start, List<T> ends){
        Objects.requireNonNull(start, "Cannot add an Edge from a null Vertex.");
        Objects.requireNonNull(ends, "Cannot add Edges from null.");
        if(ends.contains(null))
            throw new NullPointerException("Cannot add an Edge to a null Vertex.");
        if(!adjacencyList.containsKey(start))
            throw new NoSuchElementException("Cannot add the Edge because the starting Vertex does not exist in the Graph.");
        for(T end : ends){
            if(!adjacencyList.containsKey(end))
                throw new NoSuchElementException("Cannot add the Edge because the end Vertex does not exist in the Graph.");
        }
        List<T> startEdges = adjacencyList.get(start);
        startEdges.addAll(ends);
        adjacencyList.put(start, startEdges);
        for(T end : ends){
            List<T> endEdges = adjacencyList.get(end);
            endEdges.add(start);
            adjacencyList.put(end, endEdges);
        }
    }

    public void removeVertex(T vertex){
        adjacencyList.remove(vertex);
    }

    public void removeEdge(T start, T end){
        adjacencyList.get(start).remove(end);
        adjacencyList.get(end).remove(start);
    }

    public Set<T> getVertices(){
        return adjacencyList.keySet();
    }

    public int getDegree(T vertex){
        Objects.requireNonNull(vertex, "Vertex is null.");
        if(!adjacencyList.containsKey(vertex))
            throw new NoSuchElementException("Vertex not found.");
        return adjacencyList.get(vertex).size();
    }

    public List<T> getNeighbors(T vertex){
        Objects.requireNonNull(vertex, "Cannot fetch the neighbors of null.");
        return adjacencyList.get(vertex);
    }

    public boolean hasVertex(T vertex){
        return adjacencyList.containsKey(vertex);
    }

    public boolean hasEdge(T start, T end){
        return adjacencyList.get(start).contains(end);
    }

    public boolean isEmpty(){
        return adjacencyList.isEmpty();
    }

    public int vertexCount(){
        return adjacencyList.keySet().size();
    }

    public int edgeCount(){
        int edgeCount = 0;
        for(T vertex : adjacencyList.keySet()){
            edgeCount += adjacencyList.get(vertex).size();
        }
        return edgeCount;
    }

    public void clear(){
        adjacencyList = new HashMap<>();
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Graph<?>))
            return false;
        Graph<?> graph = (Graph<?>) other;
        return adjacencyList.equals(graph.adjacencyList);
    }

    @Override
    public int hashCode(){
        return adjacencyList.hashCode();
    }

    @Override
    public String toString(){
        return String.format("Graph[adjacencyList=%s]", adjacencyList);
    }
}
