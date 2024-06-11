package com.mygdx.game;

import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.GameObject.NPC;
import com.mygdx.game.GameObject.Player;
import com.mygdx.game.Map.Map;

import java.util.List;

public class GameObjectRepository {

    private final Player player;
    private final List<NPC> npcs;
    private final List<Map> maps;
    private final List<GameObject> miscObjects;

    public GameObjectRepository(List<NPC> npcs, Player player, List<Map> maps, List<GameObject> objects){
        this.npcs = npcs;
        this.player = player;
        this.maps = maps;
        this.miscObjects = objects;
    }

    public List<NPC> getNpcs(){
        return npcs;
    }

    public Player getPlayer(){
        return player;
    }

    public List<Map> getMaps(){
        return maps;
    }

    public List<GameObject> getMiscObjects(){
        return miscObjects;
    }
}
