package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.General.AnimatedGameObject;
import com.mygdx.game.General.GameObject;
import com.mygdx.game.Inventory.IInventoryManager;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;

public abstract class Character extends AnimatedGameObject {

    private final String name;
    private final IInventoryManager inventoryManager;


    public Character(TextureRegion textureRegion, Point position, Dimensions dimensions, String map, Vector translation
            , String name, IInventoryManager inventoryManager) {
        super(textureRegion, position, dimensions, map, translation);
        this.name = name;
        this.inventoryManager = inventoryManager;
    }
}
