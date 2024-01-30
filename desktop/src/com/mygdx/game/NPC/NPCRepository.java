package com.mygdx.game.NPC;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NPCRepository {

    private final List<NPC> npcs;

    public NPCRepository(List<NPC> npcs){
        this.npcs = npcs;
    }

    public List<NPC> getAllNpcs(){
        return this.npcs;
    }
}
