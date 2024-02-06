package com.mygdx.game.Drawer;

import com.mygdx.game.MyGame;
import com.mygdx.game.Player.Inventory;

public class InventoryDrawer {

    MyGame game;
    public InventoryDrawer(MyGame game){
        this.game = game;
    }

    protected void drawInventory(Inventory inventory){
        for(int i = 0; i < inventory.getSize(); i++){
            // draw items -> need item textures;
        }
    }
}
