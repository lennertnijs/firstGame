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

}
