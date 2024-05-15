package com.mygdx.game;

import com.mygdx.game.General.GameObject;
import com.mygdx.game.General.Sprite;
import com.mygdx.game.Inventory.IInventoryManager;

public final class Player extends GameObject{


    private final String name;
    private final IInventoryManager inventoryManager;
    // add player stats
    public Player(String name, Sprite sprite, IInventoryManager inventoryManager){
        super(sprite);
        this.name = name;
        this.inventoryManager = inventoryManager;
    }

    public void move(double delta){

    }
}
