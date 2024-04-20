package com.mygdx.game.V2;

import com.mygdx.game.V2.Generic.IGraph;

import java.util.List;

public interface PathFinderStrategy<T> {
    List<T> findPath(T start, T goal, IGraph<T> graph);
}
