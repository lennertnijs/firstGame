package com.mygdx.game.Controller;

import com.mygdx.game.Drawer.NPCDrawer;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.NPC.NPC;
import com.mygdx.game.Service.ClockService;
import com.mygdx.game.Service.NPCService;

import java.util.List;


public class NPCController {
    private final NPCService npcService;
    private final NPCDrawer npcDrawer;

    /**
     * Constructor for the NPC controller.
     * Loads all the NPC data from the JSON file at creation.
     */
    public NPCController(NPCService npcService, NPCDrawer npcDrawer) {
        this.npcService = npcService;
        this.npcDrawer = npcDrawer;
    }

    public void loadNPCS(){
        npcService.loadNPCS();
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
