package com.mygdx.game.Drawer;

import com.mygdx.game.NPC.NPC;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NPCDrawerRepository {

    private final List<NPCDrawer> npcDrawers;

    public NPCDrawerRepository(Builder builder){
        this.npcDrawers = builder.npcDrawers;
    }

    public List<NPCDrawer> getNpcDrawers(){
        return npcDrawers;
    }

    public void drawAllNPCS(){
        for(NPCDrawer npcDrawer: npcDrawers){
            npcDrawer.drawNPC();
        }
    }

    public List<NPC> getAllNpcs(){
        List<NPC> npcList = new ArrayList<>();
        for(NPCDrawer npcDrawer: npcDrawers){
            npcList.add(npcDrawer.getNpc());
        }
        return npcList;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private List<NPCDrawer> npcDrawers;

        private Builder(){

        }

        public Builder npcDrawers(List<NPCDrawer> npcDrawers){
            this.npcDrawers = npcDrawers;
            return this;
        }

        public NPCDrawerRepository build(){
            for(NPCDrawer drawer: npcDrawers){
                Objects.requireNonNull("The npc drawer must not be null");
            }
            return new NPCDrawerRepository(this);
        }

    }

}
