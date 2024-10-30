package com.mygdx.game.GameObject;

import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.Item;
import com.mygdx.game.Inventory.Tool;
import com.mygdx.game.Renderer.Renderer;
import com.mygdx.game.Util.Point;

import java.util.Objects;

public abstract class Character extends GameObject {

    private final String name;
    private final Inventory inventory;
    private int activeIndex;

    public Character(Renderer renderer, Point position, String map, String name, Inventory inventory) {
        super(renderer, position, map);
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
