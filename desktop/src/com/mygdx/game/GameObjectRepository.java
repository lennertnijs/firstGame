package com.mygdx.game;

import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.game_object.GameObject;
import com.mygdx.game.npc.NPC;
import com.mygdx.game.map.SingleTextureMap;

import java.util.List;

public class GameObjectRepository {

    private final List<NPC> npcs;
    private final List<SingleTextureMap> gameMaps;
    private final List<Breakable> breakables;
    private final List<GameObject> miscObjects;

    public GameObjectRepository(List<NPC> npcs, List<SingleTextureMap> gameMaps, List<Breakable> breakables, List<GameObject> objects){
        this.npcs = npcs;
        this.gameMaps = gameMaps;
        this.breakables = breakables;
        this.miscObjects = objects;
    }

    public List<NPC> getNpcs(){
        return npcs;
    }

    public List<SingleTextureMap> getMaps(){
        return gameMaps;
    }

    public List<Breakable> getBreakables(){
        return breakables;
    }


    public List<GameObject> getMiscObjects(){
        return miscObjects;
    }
}
