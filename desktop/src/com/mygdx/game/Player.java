package com.mygdx.game;

import com.mygdx.game.General.GameObject;
import com.mygdx.game.General.Sprite;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.TextureSelector.ITextureSelector;

public final class Player extends GameObject{


    private final String name;
    private final ITextureSelector selector;
    private final IInventoryManager inventoryManager;
    // add player stats
    public Player(String name, Sprite sprite, ITextureSelector selector, IInventoryManager inventoryManager){
        super(sprite);
        this.name = name;
        this.selector = selector;
        this.inventoryManager = inventoryManager;
    }
}
