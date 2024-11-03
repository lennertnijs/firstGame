package com.mygdx.game.Breakables;

import com.mygdx.game.game_object.GameObject;
import com.mygdx.game.game_object.renderer.Renderer;
import com.mygdx.game.game_object.Transform;

import java.util.Objects;

public class DroppedItem extends GameObject {

    private final String name;
    private final int amount;

    public DroppedItem(Transform transform, Renderer renderer, String map, String name, int amount){
        super(transform, renderer, map);
        this.name = Objects.requireNonNull(name, "Item name is null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or zero.");
        }
        this.amount = amount;
    }

    public String name(){
        return name;
    }

    public int amount(){
        return amount;
    }

    @Override
    public String toString(){
        return String.format("DroppedItem[position=%s, width=%d, height=%d, map=%s, name=%s, amount =%d]",
                super.getPosition(), getWidth(), getHeight(), map, name, amount);
    }
}
