package com.mygdx.game.Controller;

import com.mygdx.game.Entity.Position;
import com.mygdx.game.NPC.NPC;
import com.mygdx.game.Service.NPCService;

import java.util.List;


public class NPCController {
    private final NPCService npcService;

    /**
     * Constructor for the NPC controller.
     * Loads all the NPC data from the JSON file at creation.
     */
    public NPCController(NPCService npcService) {
        this.npcService = npcService;
    }


    public void updateNPCs(){
        npcService.updateNPCS();
    }


    public List<NPC> getNPCS(){
        return npcService.getAllNPCS();
    }

    public boolean checkCollision(Position position){
        return npcService.collides(position);
    }
}
