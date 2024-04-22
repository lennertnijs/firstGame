package com.mygdx.game.V2.Graph;

import java.util.*;
import java.util.stream.Collectors;

public final class Graph<T> implements IGraph<T>{

    private Map<Vertex<T>, List<Edge<T>>> adjacencyMap;

    public Graph(){
        this.adjacencyMap = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addVertex(T object){
        Vertex<T> vertex = new Vertex<>(object);
        if(adjacencyMap.containsKey(vertex))
            throw new IllegalStateException("The Vertex already exists in the Graph.");
        adjacencyMap.put(vertex, new ArrayList<>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addVertices(List<T> objects){
        Objects.requireNonNull(objects, "List is null.");
        for(T object : objects)
            addVertex(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEdge(T start, T end){
        createAndStoreEdge(start, end, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEdge(T start, T end, int weight) {
        createAndStoreEdge(start, end, weight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEdges(T start, List<T> ends){
        Objects.requireNonNull(ends, "List is null.");
        for(T end : ends)
            createAndStoreEdge(start, end, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEdges(T start, List<T> ends, List<Integer> weights) {
        Objects.requireNonNull(ends, "List is null.");
        Objects.requireNonNull(weights, "List is null.");
        if(weights.size() != ends.size())
            throw new IllegalArgumentException("The List of weights is not the same length as the list of end objects.");
        for(int i = 0; i < ends.size(); i++)
            createAndStoreEdge(start, ends.get(i), weights.get(i));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect(T start, T end) {
        createAndStoreEdge(start, end, 0);
        createAndStoreEdge(end, start, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect(T start, List<T> ends) {
        Objects.requireNonNull(ends, "List is null.");
        for(T end : ends) {
            createAndStoreEdge(start, end, 0);
            createAndStoreEdge(end, start, 0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect(T start, T end, int weight) {
        createAndStoreEdge(start, end, weight);
        createAndStoreEdge(end, start, weight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect(T start, List<T> ends, List<Integer> weights) {
        Objects.requireNonNull(ends, "List is null.");
        Objects.requireNonNull(weights, "List is null.");
        if(weights.size() != ends.size())
            throw new IllegalArgumentException("The List of weights is not the same length as the list of end objects.");
        for(int i = 0; i < ends.size(); i++){
            createAndStoreEdge(start, ends.get(i), weights.get(i));
            createAndStoreEdge(ends.get(i), start, weights.get(i));
        }
    }

    /**
     * Creates and stores an edge starting at the given object and ending in the other, with the given weight.
     * @param start The start object. Cannot be null.
     * @param end The end object. Cannot be null.
     * @param weight The weight. Cannot be negative.
     *
     * @throws IllegalArgumentException If the weight is negative.
     * @throws NoSuchElementException If the start or end object is not a vertex in the graph.
     */
    private void createAndStoreEdge(T start, T end, int weight){
        Vertex<T> startVertex = new Vertex<>(start);
        Vertex<T> endVertex = new Vertex<>(end);
        if(weight < 0)
            throw new IllegalArgumentException("The weight is negative.");
        if(!adjacencyMap.containsKey(startVertex))
            throw new NoSuchElementException("Starting Vertex is not part of the Graph.");
        if(!adjacencyMap.containsKey(endVertex))
            throw new NoSuchElementException("Ending Vertex is not part of the Graph.");
        Edge<T> edge = new Edge<>(startVertex, endVertex, weight);
        adjacencyMap.get(startVertex).add(edge);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeVertex(T object){
        adjacencyMap.remove(new Vertex<>(object));
        for(List<Edge<T>> edgeList : adjacencyMap.values()){
            edgeList.removeIf(edge -> edge.getEnd().equals(object));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEdge(T start, T end){
        Vertex<T> startVertex = new Vertex<>(start);
        Vertex<T> endVertex = new Vertex<>(end);
        if(!adjacencyMap.containsKey(startVertex))
            throw new NoSuchElementException("Starting vertex is not part of the Graph.");
        if(!adjacencyMap.containsKey(endVertex))
            throw new NoSuchElementException("End vertex is not part of the Graph.");
        adjacencyMap.get(startVertex).removeIf(edge -> edge.getStart().equals(startVertex) && edge.getEnd().equals(endVertex));
        adjacencyMap.get(endVertex).removeIf(edge -> edge.getStart().equals(endVertex) && edge.getEnd().equals(startVertex));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void disconnect(T start, T end) {
        removeEdge(start, end);
        removeEdge(end, start);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getVertices(){
        return adjacencyMap.keySet().stream().map(Vertex::getValue).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getNeighbors(T object) {
        Vertex<T> vertex = new Vertex<>(object);
        if(!adjacencyMap.containsKey(vertex))
            throw new NoSuchElementException("The object is not a vertex in the Graph.");
        return adjacencyMap.get(vertex).stream()
                .map(Edge::getEnd)
                .map(Vertex::getValue)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc} ACTIVELY WORKING HERE.
     */
    @Override
    public int getWeight(T start, T end){
        Vertex<T> startVertex = new Vertex<>(start);
        Vertex<T> endVertex = new Vertex<>(end);
        // todo
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDegree(T value){
        Vertex<T> vertex = new Vertex<>(value);
        if(!adjacencyMap.containsKey(vertex))
            throw new NoSuchElementException("Vertex not found.");
        return adjacencyMap.get(vertex).size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasVertex(T t){
        return adjacencyMap.containsKey(new Vertex<>(t));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasEdge(T start, T end){
        Vertex<T> startVertex = new Vertex<>(start);
        Vertex<T> endVertex = new Vertex<>(end);
        if(!adjacencyMap.containsKey(startVertex))
            throw new NoSuchElementException("Start Vertex not found.");
        for(Edge<T> edge : adjacencyMap.get(startVertex)){
            if(edge.getStart().equals(startVertex) && edge.getEnd().equals(endVertex))
                return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty(){
        return adjacencyMap.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int vertexCount(){
        return adjacencyMap.keySet().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int edgeCount(){
        int edgeCount = 0;
        for(Vertex<T> vertex : adjacencyMap.keySet()){
            edgeCount += adjacencyMap.get(vertex).size();
        }
        return edgeCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
