package com.mygdx.game.V2.Generic;

import java.util.List;

public interface IUnweightedGraph<T> extends IGraph<T> {

    void addEdge(T startVertex, T endVertex);
    void addEdges(T startVertex, List<T> endVertices);
}
