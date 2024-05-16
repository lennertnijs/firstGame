package com.mygdx.game;

import com.mygdx.game.NPC.NPC;

import java.util.List;

public class GameObjectRepository {

    private final List<NPC> npcs;

    public GameObjectRepository(List<NPC> npcs){
        this.npcs = npcs;
    }

    public List<NPC> getNpcs(){
        return npcs;
    }
}
