package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Util.GameMap;

import java.util.ArrayList;
import java.util.List;

public final class MapLoader {

    public static List<GameMap> loadAll(){
        List<GameMap> gameMaps = new ArrayList<>();
        gameMaps.add(new GameMap("main", new Texture(Gdx.files.internal("maps/map.png"))));
        return gameMaps;
    }
}
