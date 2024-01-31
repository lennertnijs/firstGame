package com.mygdx.game.Drawer;

import com.mygdx.game.MyGame;

public class DrawerController {

    MyGame game;
    NPCDrawer npcDrawer;
    PlayerDrawer playerDrawer;
    StoneDrawer stoneDrawer;

    public DrawerController(MyGame game){
        npcDrawer = new NPCDrawer(game);
        //playerDrawer = new PlayerDrawer(game);
        stoneDrawer = new StoneDrawer(game);
    }
}
