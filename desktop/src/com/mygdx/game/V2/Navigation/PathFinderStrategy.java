package com.mygdx.game.V2.Navigation;

import java.util.List;

public interface PathFinderStrategy<T> {
    List<T> findPath(T start, T goal);
}
