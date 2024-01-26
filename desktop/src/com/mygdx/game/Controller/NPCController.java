package com.mygdx.game.Controller;

import com.mygdx.game.Clock.Clock;
import com.mygdx.game.Service.NPCService;


public class NPCController {

    final Clock clock;
    final NPCService npcService;

    /**
     * Constructor for the NPC controller.
     * Loads all the NPC data from the JSON file at creation.
     */
    public NPCController(Clock clock, NPCService npcService) {
        this.clock = clock;
        this.npcService = npcService;
    }

    public void updateNPCs(){
        npcService.updateNPCS();
    }
}
