package com.mygdx.game.V2.Graph;

import java.util.*;

public final class Graph<T> implements IGraph<T>{

    private Map<Vertex<T>, List<Edge<T>>> adjacencyMap;

    public Graph(){
        this.adjacencyMap = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addVertex(T t){
        Vertex<T> vertex = new Vertex<>(t);
        if(adjacencyMap.containsKey(vertex))
            throw new IllegalStateException("The value already exists in the Graph.");
        adjacencyMap.put(vertex, new ArrayList<>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addVertices(List<T> values){
        Objects.requireNonNull(values, "Values list is null.");
        for(T value : values)
            addVertex(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEdge(T start, T end){
        Vertex<T> startVertex = new Vertex<>(start);
        Vertex<T> endVertex = new Vertex<>(end);
        createAndStoreEdge(startVertex, endVertex, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEdge(T start, T end, int weight) {
        Vertex<T> startVertex = new Vertex<>(start);
        Vertex<T> endVertex = new Vertex<>(end);
        if(weight < 0)
            throw new IllegalArgumentException("The weight is negative.");
        createAndStoreEdge(startVertex, endVertex, weight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEdges(T start, List<T> ends){
        Objects.requireNonNull(ends, "End values list is null.");
        for(T value : ends)
            addEdge(start, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEdges(T startVertex, List<T> endVertices, List<Integer> weights) {
        //todo
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect(T start, T end) {
        Vertex<T> startVertex = new Vertex<>(start);
        Vertex<T> endVertex = new Vertex<>(end);
        createAndStoreEdge(startVertex, endVertex, 0);
        createAndStoreEdge(endVertex, startVertex, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect(T start, List<T> end) {
        Vertex<T> startVertex = new Vertex<>(start);
        for(T value : end) {
            Vertex<T> endVertex = new Vertex<>(value);
            createAndStoreEdge(startVertex, endVertex, 0);
            createAndStoreEdge(endVertex, startVertex, 0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect(T start, T end, int weight) {
        Vertex<T> startVertex = new Vertex<>(start);
        Vertex<T> endVertex = new Vertex<>(end);
        createAndStoreEdge(startVertex, endVertex, weight);
        createAndStoreEdge(endVertex, startVertex, weight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect(T start, List<T> end, List<Integer> weights) {
        Vertex<T> startVertex = new Vertex<>(start);
        int i = 0;
        for(T value : end) {
            Vertex<T> endVertex = new Vertex<>(value);
            createAndStoreEdge(startVertex, endVertex, weights.get(i));
            createAndStoreEdge(endVertex, startVertex, weights.get(i));
            i++;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disconnect(T start, T end) {
        Vertex<T> startVertex = new Vertex<>(start);
        Vertex<T> endVertex = new Vertex<>(end);
        adjacencyMap.get(startVertex).removeIf(edge -> edge.getStart().equals(startVertex) && edge.getEnd().equals(endVertex));
        adjacencyMap.get(startVertex).removeIf(edge -> edge.getStart().equals(endVertex) && edge.getEnd().equals(startVertex));
    }

    private void createAndStoreEdge(Vertex<T> startVertex, Vertex<T> endVertex, int weight){
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
    public void removeVertex(T value){
        adjacencyMap.remove(new Vertex<>(value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEdge(T start, T end){
        Vertex<T> v1 = new Vertex<>(start);
        Vertex<T> v2 = new Vertex<>(end);
        if(!adjacencyMap.containsKey(v1))
            throw new NoSuchElementException("No vertex.");
        if(!adjacencyMap.containsKey(v2))
            throw new NoSuchElementException("No vertex.");
        adjacencyMap.get(v1).removeIf(edge -> edge.getStart().equals(v1) && edge.getEnd().equals(v2));
        adjacencyMap.get(v2).removeIf(edge -> edge.getStart().equals(v2) && edge.getEnd().equals(v1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getVertices(){
        List<T> valuesSet = new ArrayList<>();
        for(Vertex<T> vertex : adjacencyMap.keySet())
            valuesSet.add(vertex.getValue());
        return valuesSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getNeighbors(T vertex) {
        return null;
    }

    /**
     * {@inheritDoc}
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
