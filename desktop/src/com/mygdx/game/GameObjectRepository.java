package com.mygdx.game;

import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.GameObject.NPC;
import com.mygdx.game.Map.Map;

import java.util.List;

public class GameObjectRepository {

    private final List<NPC> npcs;
    private final List<Map> maps;
    private final List<GameObject> miscObjects;

    public GameObjectRepository(List<NPC> npcs, List<Map> maps, List<GameObject> objects){
        this.npcs = npcs;
        this.maps = maps;
        this.miscObjects = objects;
    }

    public List<NPC> getNpcs(){
        return npcs;
    }

    public List<Map> getMaps(){
        return maps;
    }

    public List<GameObject> getMiscObjects(){
        return miscObjects;
    }
}
