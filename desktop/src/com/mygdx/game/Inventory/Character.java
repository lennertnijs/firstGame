package com.mygdx.game.Inventory;

import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.GameObject.Renderer;
import com.mygdx.game.GameObject.Transform;

import java.util.Objects;

public abstract class Character extends GameObject {

    private final String name;
    private final Inventory inventory;
    private int activeIndex;

    public Character(Transform transform, Renderer renderer, String map, String name, Inventory inventory) {
        super(transform, renderer, map);
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
