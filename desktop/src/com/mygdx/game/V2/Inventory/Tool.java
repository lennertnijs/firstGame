package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.Breakables.Breakable;
import com.mygdx.game.V2.Breakables.BreakableType;

import java.util.Objects;

public final class Tool extends Item{


    private final int efficiency;
    private int durability;
    private final ToolType type;

    public Tool(String name, int efficiency, int durability, ToolType type){
        super(name, 1, 1);
        if(efficiency < 0) {
            throw new IllegalArgumentException("Efficiency is negative.");
        }
        if(durability < 0) {
            throw new IllegalArgumentException("Durability is negative.");
        }
        Objects.requireNonNull(type, "Type is null.");
        this.efficiency = efficiency;
        this.durability = durability;
        this.type = type;
    }

    public int getEfficiency(){
        return efficiency;
    }

    public int getDurability(){
        return durability;
    }

    public ToolType getType(){
        return type;
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

    public void use(Breakable b){
        if(checkToolAndBreakableMatch(b)){
            b.damage(efficiency);
        }
        // handle monsters with sword.
    }

    private boolean checkToolAndBreakableMatch(Breakable b){
        return b.getType() == BreakableType.TREE && type == ToolType.AXE ||
                b.getType() == BreakableType.ROCK && type == ToolType.PICKAXE ||
                b.getType() == BreakableType.SAND && type == ToolType.SHOVEL;
    }


    @Override
    public boolean equals(Object other){
        if(!(other instanceof Tool)){
            return false;
        }
        if(!super.equals(other)) {
            return false;
        }
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

    public Tool copy(){
        return new Tool(getName(), efficiency, durability, type);
    };
}
