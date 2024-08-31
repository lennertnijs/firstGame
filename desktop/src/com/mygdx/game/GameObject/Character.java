package com.mygdx.game.GameObject;

import com.mygdx.game.Animation.AnimationHolder;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.Item;
import com.mygdx.game.Inventory.Tool;
import com.mygdx.game.Util.*;

import java.util.Objects;

public abstract class Character extends Entity {

    private final String name;
    private final Inventory inventory;
    private int activeIndex;

    public Character(Point position,
                     Dimensions dimensions,
                     String map,
                     AnimationHolder animationHolder,
                     double delta,
                     Direction direction,
                     String name,
                     Inventory inventory) {
        super(position, dimensions, map, animationHolder, delta, direction);
        this.name = Objects.requireNonNull(name, "Name is null.");
        this.inventory = inventory;
    }

    public String getName(){
        return name;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public Item getActiveItem(){
        return inventory.getItem(activeIndex);
    }

    public boolean hasToolInActive(){
        return inventory.getItem(activeIndex) instanceof Tool;
    }

    public int getActiveIndex(){
        return activeIndex;
    }

    public void incrementActiveIndex(){
        this.activeIndex = (activeIndex + 1) % inventory.size();
    }
}
