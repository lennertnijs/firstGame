package com.mygdx.game.Controller;

import com.mygdx.game.Drawer.NPCDrawer;
import com.mygdx.game.Service.ClockService;
import com.mygdx.game.Service.NPCService;


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
        npcDrawer.loadNPCTextures(npcService.getAllNPCS());
    }

    public void updateNPCs(){
        npcService.updateNPCS();
        npcDrawer.drawAllNPCS(npcService.getAllNPCS());
    }
}
