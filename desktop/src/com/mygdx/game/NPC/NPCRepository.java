package com.mygdx.game.NPC;

import com.mygdx.game.DAO.NPCDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NPCRepository {

    private final List<NPC> npcs;

    public NPCRepository(){
        this.npcs = new ArrayList<>();
        // this.npcs = new NPCDAO().readNPCS();
    }

    public List<NPC> getAllNpcs(){
        return this.npcs;
    }
}
