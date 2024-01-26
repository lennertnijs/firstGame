package com.mygdx.game.Controller;

import com.mygdx.game.Clock.Clock;
import com.mygdx.game.Drawer.NPCDrawer;
import com.mygdx.game.Service.NPCService;


public class NPCController {
    final NPCService npcService;
    final NPCDrawer npcDrawer;

    /**
     * Constructor for the NPC controller.
     * Loads all the NPC data from the JSON file at creation.
     */
    public NPCController(Clock clock, NPCDrawer npcDrawer) {
        this.npcService = new NPCService(clock);
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
