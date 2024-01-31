package com.mygdx.game.Controller;

import com.mygdx.game.Entity.Position;
import com.mygdx.game.NPC.NPC;
import com.mygdx.game.Service.NPCService;

import java.util.List;


public class NPCController {

    private final NPCService npcService;
    private final ClockController clockController;

    /**
     * Constructor for the NPC controller.
     * Loads all the NPC data from the JSON file at creation.
     */
    public NPCController(ClockController clockController) {
        this.npcService = new NPCService();
        this.clockController = clockController;
    }


    public void updateNPCs(){
        npcService.updateNPCS(clockController.getClock());
    }


    public List<NPC> getNPCS(){
        return npcService.getAllNPCS();
    }

    public boolean checkCollision(Position position){
        return npcService.collides(position);
    }
}
