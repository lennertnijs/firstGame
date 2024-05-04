package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.General.GameObject;

public abstract class Tool extends Item{


    private final int efficiency;
    private int durability;

    public Tool(int amount, ItemTemplate template, int efficiency, int durability){
        super(template, amount);
        this.efficiency = efficiency;
        this.durability = durability;
    }

    public abstract void use(GameObject object);

    public int getEfficiency(){
        return efficiency;
    }

    public int getDurability(){
        return durability;
    }

    public void setDurability(int durability){
        if(durability < 0)
            throw new IllegalArgumentException("Durability is negative.");
        this.durability = durability;
    }


}
