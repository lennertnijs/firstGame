package com.mygdx.game.DAO;

import com.mygdx.game.map.SingleTextureMap;

import java.util.ArrayList;
import java.util.List;

public final class MapLoader {

    public static List<SingleTextureMap> loadAll(){
        List<SingleTextureMap> gameMaps = new ArrayList<>();
        gameMaps.add(new SingleTextureMap("main", new int[][]{{1}}));
        return gameMaps;
    }
}
