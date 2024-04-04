package com.mygdx.game.V2;

import java.util.List;

public interface PathFinderStrategy<T> {
    List<T> findPath(T start, T goal, Graph<T> graph);
}
