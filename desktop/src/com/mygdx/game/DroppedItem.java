package com.mygdx.game;

import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.GameObject.Renderer;
import com.mygdx.game.GameObject.Transform;

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
    public boolean equals(Object other){
        if(!(other instanceof DroppedItem))
            return false;
        if(!super.equals(other))
            return false;
        DroppedItem item = (DroppedItem) other;
        return name.equals(item.name) &&    amount == item.amount;
    }

    @Override
    public int hashCode(){
        int result = super.hashCode();
        result = result * 31 + name.hashCode();
        result = result * 31 + amount;
        return result;
    }

    @Override
    public String toString(){
        return String.format("DroppedItem[position=%s, dimensions=%s, map=%s, name=%s, amount =%d]",
                super.getPosition(), super.getDimensions(), map, name, amount);
    }
}
