package com.mygdx.game.V2;

import java.util.List;
import java.util.Map;

public interface PathFinderStrategy<T> {
    List<T> findPath(T start, T goal, Map<T, List<T>> network);
}
