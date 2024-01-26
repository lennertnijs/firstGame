package com.mygdx.game.NPC;

import java.util.ArrayList;
import java.util.Objects;

public class NPCRepository {

    private final ArrayList<NPC> npcs = new ArrayList<>();

    public NPCRepository(){

    }

    public ArrayList<NPC> getNpcs(){
        return this.npcs;
    }

    public void add(NPC npc){
        Objects.requireNonNull(npc, "Cannot put a null NPC into the repository");
        npcs.add(npc);
    }
}
