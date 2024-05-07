package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.General.GameObject;

public abstract class Tool extends Item{


    private final int efficiency;
    private int durability;

    public Tool(String name, int efficiency, int durability){
        super(name, 1, 1);
        if(efficiency < 0) {
            throw new IllegalArgumentException("Efficiency is negative.");
        }
        if(durability < 0) {
            throw new IllegalArgumentException("Durability is negative.");
        }
        this.efficiency = efficiency;
        this.durability = durability;
    }

    public int getEfficiency(){
        return efficiency;
    }

    public int getDurability(){
        return durability;
    }

    public boolean hasDurabilityLeft(){
        return durability > 0;
    }

    public void setDurability(int durability){
        if(durability < 0) {
            throw new IllegalArgumentException("Durability is negative.");
        }
        this.durability = durability;
    }

    public void decrementDurability(){
        setDurability(durability - 1);
    }

    public abstract void use(GameObject object);


    @Override
    public boolean equals(Object other){
        if(!super.equals(other))
            return false;
        Tool tool = (Tool) other;
        return efficiency == tool.efficiency && durability == tool.durability;
    }

    @Override
    public int hashCode(){
        int result = super.hashCode();
        result = result * 31 + efficiency;
        result = result * 31 + durability;
        return result;
    }
}
