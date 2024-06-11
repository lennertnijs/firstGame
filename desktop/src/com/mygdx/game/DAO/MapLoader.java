package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Map.GameMap;
import com.mygdx.game.Map.Map;

import java.util.ArrayList;
import java.util.List;

public final class MapLoader {

    public static List<Map> loadAll(){
        List<Map> maps = new ArrayList<>();
        maps.add(new Map(new TextureRegion(new Texture(Gdx.files.internal("maps/map.png"))), GameMap.MAIN));
        return maps;
    }
}
