package com.mygdx.game.V2;

import java.util.List;
import java.util.Map;

public interface PathFinderStrategy {
    Route findPath(MapPosition start, MapPosition goal, Map<MapPosition, List<MapPosition>> network);
}
