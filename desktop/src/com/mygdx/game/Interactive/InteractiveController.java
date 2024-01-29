package com.mygdx.game.Interactive;

import com.mygdx.game.Entity.Position;
import com.mygdx.game.NPC.NPC;
import com.mygdx.game.Service.NPCService;

import static com.mygdx.game.Constants.*;
import static com.mygdx.game.Constants.PLAYER_HEIGHT;

public class InteractiveController {

    private final NPCService npcService;
    public InteractiveController(NPCService npcService){
        this.npcService = npcService;
    }

    public Interactive checkInteractions(int x, int y){
        for(NPC npc: npcService.getAllNPCS()) {
            int npcX = npc.getPosition().getX();
            int npcY = npc.getPosition().getY();
            boolean collides = x <= npcX + NPC_WIDTH && npcX <= x + PLAYER_WIDTH &&
                    y <= npcY + NPC_HEIGHT && npcY <= y + PLAYER_HEIGHT;
            if(collides){
                return npc;
            }
        }
        return null;
    }
}
