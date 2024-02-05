package com.mygdx.game.Drawer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Controller.NPCController;
import com.mygdx.game.MyGame;
import com.mygdx.game.NPC.NPC;
import com.mygdx.game.Player.PlayerController;
import com.mygdx.game.Stone.Stone;
import com.mygdx.game.Controller.StoneController;

public class DrawerGod {

    MyGame game;
    NPCDrawer npcDrawer;
    NPCController npcController;
    PlayerDrawer playerDrawer;

    PlayerController playerController;
    StoneDrawer stoneDrawer;

    StoneController stoneController;

    Texture map = new Texture(Gdx.files.internal("images/untitled.png"));

    public DrawerGod(MyGame game, NPCController npcController, PlayerController playerController, StoneController stoneController){
        this.game = game;
        npcDrawer = new NPCDrawer(game);
        playerDrawer = new PlayerDrawer(game, playerController);
        stoneDrawer = new StoneDrawer(game);
        this.npcController = npcController;
        this.playerController = playerController;
        this.stoneController = stoneController;
    }

    public void drawAll(){
        game.batch.draw(map, 0, 0, 4800 ,4800 );
        drawStones();
        drawNPCS();
        drawPlayer();
    }
    private void drawPlayer(){
        playerDrawer.drawPlayer();
    }
    private void drawNPCS(){
        for(NPC npc: npcController.getNPCS()){
            npcDrawer.drawNPC(npc);
        }
    }



    private void drawStones(){
        for(Stone stone: stoneController.getAllStones()){
            stoneDrawer.drawStone(stone);
        }
    }
}
