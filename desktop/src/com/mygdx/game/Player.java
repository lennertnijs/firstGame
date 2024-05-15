package com.mygdx.game;

import com.mygdx.game.General.GameObject;
import com.mygdx.game.General.Sprite;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.TextureSelector.IAnimationManager;

public final class Player extends GameObject{


    private final String name;
    private final IAnimationManager animationManager;
    private final IInventoryManager inventoryManager;
    // add player stats
    public Player(String name, Sprite sprite, IAnimationManager animationManager, IInventoryManager inventoryManager){
        super(sprite);
        this.name = name;
        this.animationManager = animationManager;
        this.inventoryManager = inventoryManager;
    }
}
