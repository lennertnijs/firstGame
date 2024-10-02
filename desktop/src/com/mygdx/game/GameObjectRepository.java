package com.mygdx.game;

import com.mygdx.game.Bat.Bat;
import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.npc.NPC;
import com.mygdx.game.Util.GameMap;

import java.util.List;

public class GameObjectRepository {

    private final List<NPC> npcs;
    private final List<GameMap> gameMaps;
    private final List<Breakable> breakables;
    private final List<Bat> bats;
    private final List<GameObject> miscObjects;

    public GameObjectRepository(List<NPC> npcs, List<GameMap> gameMaps, List<Breakable> breakables, List<Bat> bats, List<GameObject> objects){
        this.npcs = npcs;
        this.gameMaps = gameMaps;
        this.breakables = breakables;
        this.bats = bats;
        this.miscObjects = objects;
    }

    public List<NPC> getNpcs(){
        return npcs;
    }

    public List<GameMap> getMaps(){
        return gameMaps;
    }

    public List<Breakable> getBreakables(){
        return breakables;
    }

    public List<Bat> getBats(){
        return bats;
    }

    public List<GameObject> getMiscObjects(){
        return miscObjects;
    }
}
