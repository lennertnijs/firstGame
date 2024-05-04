package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.General.GameObject;

/**
 * Represents a usable tool in the game. Tools include things like axes, pickaxes, weapons, etc...
 */
public abstract class Tool extends Item{


    private final int efficiency;
    private int durability;

    /**
     * Creates a new {@link Tool}.
     * A tool has a default amount of 1.
     * @param template The item template. Cannot be null.
     * @param efficiency The efficiency. Cannot be negative.
     * @param durability The durability. Cannot be negative.
     */
    public Tool(ItemTemplate template, int efficiency, int durability){
        super(template, 1);
        if(efficiency < 0)
            throw new IllegalArgumentException("Efficiency is negative.");
        if(durability < 0)
            throw new IllegalArgumentException("Durability is negative.");
        this.efficiency = efficiency;
        this.durability = durability;
    }

    public int getEfficiency(){
        return efficiency;
    }

    public int getDurability(){
        return durability;
    }

    /**
     * Sets the durability of this item to the given durability.
     * @param durability The new durability. Cannot be negative.
     */
    public void setDurability(int durability){
        if(durability < 0)
            throw new IllegalArgumentException("Durability is negative.");
        this.durability = durability;
    }

    /**
     * Decrements the durability of this item by 1.
     */
    public void decrementDurability(){
        setDurability(durability - 1);
    }

    /**
     * @return True if this tool has any durability left. False otherwise.
     */
    public boolean hasDurabilityLeft(){
        return durability > 0;
    }

    /**
     * Abstract method each tool will implement for tool usage.
     * @param object The game object that it should be used on.
     */
    public abstract void use(GameObject object);


    @Override
    public boolean equals(Object other){
        if(!(other instanceof Tool))
            return false;
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
