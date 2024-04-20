package com.mygdx.game.V2.Generic;

import java.util.List;
import java.util.Set;

public interface IGraph<T> {

    void addVertex(T vertex);
    void addVertices(List<T> vertices);
    void addEdge(T startVertex, T endVertex);
    void addEdges(T startVertex, List<T> endVertices);
//    void addEdge(T startVertex, T endVertex, int weight);
//    void addEdges(T startVertex, List<T> endVertices, List<Integer> weights);
    void removeVertex(T vertex);
    void removeEdge(T startVertex, T endVertex);
    Set<T> getVertices();
    List<T> getNeighbors(T vertex);
    int getDegree(T vertex);
    boolean hasVertex(T vertex);
    boolean hasEdge(T startVertex, T endVertex);
    boolean isEmpty();
    int vertexCount();
    int edgeCount();
    void clear();
}
