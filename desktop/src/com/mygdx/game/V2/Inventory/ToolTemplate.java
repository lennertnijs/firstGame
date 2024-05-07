package com.mygdx.game.V2.Inventory;

import com.badlogic.gdx.graphics.Texture;

public final class ToolTemplate extends ItemTemplate{

    private final int efficiency;
    private final int maxDurability;

    public ToolTemplate(String name, String description, Texture texture, int stackSize, int efficiency, int maxDurability) {
        super(name, description, texture, stackSize);
        if(efficiency <= 0){
            throw new IllegalArgumentException("Efficiency is negative or 0.");
        }
        if(maxDurability <= 0){
            throw new IllegalArgumentException("Max durability is null.");
        }
        this.efficiency = efficiency;
        this.maxDurability = maxDurability;
    }

    public int efficiency(){
        return efficiency;
    }

    public int maxDurability(){
        return maxDurability;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof ToolTemplate)){
            return false;
        }
        if(!super.equals(other)){
            return false;
        }
        ToolTemplate template = (ToolTemplate) other;
        return efficiency == template.efficiency && maxDurability == template.maxDurability;
    }

    @Override
    public int hashCode(){
        int result = super.hashCode();
        result = result * 31 + efficiency;
        result = result * 31 + maxDurability;
        return result;
    }

    @Override
    public String toString(){
        return String.format("ToolTemplate[itemStuff=%s, efficiency=%d, maxDurability=%d]",
                super.toString(), efficiency, maxDurability);
    }




}
