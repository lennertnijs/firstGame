package com.mygdx.game.General;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;

import java.util.Objects;

public class DroppedGameObject extends GameObject{

    private final String itemName;
    private final int amount;

    public DroppedGameObject(TextureRegion textureRegion, Point position, Dimensions dimensions, String map,
                             String itemName, int amount){
        super(textureRegion, position, dimensions, map);
        this.itemName = Objects.requireNonNull(itemName, "Item name is null.");
        this.amount = amount;
    }

}
