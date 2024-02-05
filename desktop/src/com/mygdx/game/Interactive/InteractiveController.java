package com.mygdx.game.Interactive;

import com.mygdx.game.Stone.Stone;
import com.mygdx.game.Controller.StoneController;
import com.mygdx.game.Controller.NPCController;
import com.mygdx.game.NPC.NPC;

import static com.mygdx.game.Constants.*;
import static com.mygdx.game.Constants.PLAYER_HEIGHT;

public class InteractiveController {

    private final NPCController npcController;
    private final StoneController stoneController;

    public InteractiveController(NPCController npcController, StoneController stoneController){
        this.npcController = npcController;
        this.stoneController = stoneController;
    }

    public Interactive checkInteractions(int x, int y){
        Interactive interactive = checkNPCInteractions(x, y);
        if(interactive != null){
            return interactive;
        }
        return checkStoneInteractions(x,y);
    }

    public Interactive checkNPCInteractions(int x, int y){
        for(NPC npc: npcController.getNPCS()) {
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

    public Interactive checkStoneInteractions(int x, int y){
        for(Stone stone: stoneController.getAllStones()) {
            int stoneX = stone.getPosition().getX();
            int stoneY = stone.getPosition().getY();
            boolean collides = x <= stoneX + STONE_WIDTH && stoneX <= x + PLAYER_WIDTH &&
                    y <= stoneY + STONE_HEIGHT && stoneY <= y + PLAYER_HEIGHT;
            if(collides){
                return stone;
            }
        }
        return null;
    }
}
