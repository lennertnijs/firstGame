package com.mygdx.game.V2.Generic;

import java.util.*;

public final class Graph<T> implements IGraph<T>{

    private Map<Vertex<T>, List<Edge<T>>> adjacencyMap;

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

    public void addVertex(T t){
        Vertex<T> vertex = new Vertex<>(t);
        if(adjacencyMap.containsKey(vertex))
            throw new IllegalArgumentException("The value already exists in the Graph.");
        adjacencyMap.put(vertex, new ArrayList<>());
    }

    public void addVertices(List<T> values){
        Objects.requireNonNull(values, "Values list is null.");
        for(T value : values)
            addVertex(value);
    }

    public void addEdge(T start, T end){
        Vertex<T> startVertex = new Vertex<>(start);
        if(!adjacencyMap.containsKey(startVertex))
            throw new NoSuchElementException("Starting Vertex is not part of the Graph.");
        Vertex<T> endVertex = new Vertex<>(end);
        if(!adjacencyMap.containsKey(endVertex))
            throw new NoSuchElementException("Ending Vertex is not part of the Graph.");

        Edge<T> edge1 = new Edge<>(startVertex, endVertex, 0);
        adjacencyMap.get(startVertex).add(edge1);
        Edge<T> edge2 = new Edge<>(endVertex, startVertex, 0);
        adjacencyMap.get(endVertex).add(edge2);
    }

    public void addEdges(T start, List<T> ends){
        Objects.requireNonNull(ends, "End values list is null.");
        for(T value : ends)
            addEdge(start, value);

    }

    public void removeVertex(T value){
        adjacencyMap.remove(new Vertex<>(value));
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

    public boolean hasVertex(T t){
        return adjacencyMap.containsKey(new Vertex<>(t));
    }

    public boolean hasEdge(T start, T end){
        return adjacencyMap.get(start).contains(end);
    }

    /**
     * @return True if the {@link Graph} is empty. False otherwise.
     */
    public boolean isEmpty(){
        return adjacencyMap.isEmpty();
    }

    /**
     * @return The amount of vertices in this {@link Graph}.
     */
    public int vertexCount(){
        return adjacencyMap.keySet().size();
    }

    /**
     * @return The amount of edges in this {@link Graph}.
     */
    public int edgeCount(){
        int edgeCount = 0;
        for(Vertex<T> vertex : adjacencyMap.keySet()){
            edgeCount += adjacencyMap.get(vertex).size();
        }
        return edgeCount;
    }

    /**
     * Clears the entire {@link Graph}.
     */
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
