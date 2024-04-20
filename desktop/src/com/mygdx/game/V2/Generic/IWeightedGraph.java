package com.mygdx.game.V2.Generic;

import java.util.List;

public interface IWeightedGraph<T> extends IGraph<T>{

    void addEdge(T startVertex, T endVertex, int weight);
    void addEdges(T startVertex, List<T> endVertices, List<Integer> weights);
}
