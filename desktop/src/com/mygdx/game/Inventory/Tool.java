package com.mygdx.game.Inventory;

import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.Breakables.BreakableType;

import java.util.Objects;

public final class Tool extends Item{


    private final int efficiency;
    private final int maxDurability;
    private int durability;
    private final ToolType type;

    public Tool(String name, int efficiency, int maxDurability, int durability, ToolType type){
        super(name, 1, 1);
        if(efficiency < 0) {
            throw new IllegalArgumentException("Efficiency is negative.");
        }
        if(maxDurability <= 0){
            throw new IllegalArgumentException("Max durability is negative or zero.");
        }
        if(durability < 0) {
            throw new IllegalArgumentException("Durability is negative.");
        }
        if(durability > maxDurability){
            throw new IllegalArgumentException("Durability is bigger than the max durability.");
        }
        Objects.requireNonNull(type, "Type is null.");
        this.efficiency = efficiency;
        this.maxDurability = maxDurability;
        this.durability = durability;
        this.type = type;
    }

    public int efficiency(){
        return efficiency;
    }

    public int maxDurability(){
        return maxDurability;
    }

    public int getDurability(){
        return durability;
    }

    public void setDurability(int durability){
        if(durability < 0 || durability > maxDurability){
            throw new IllegalArgumentException("Durability is negative or bigger than the max durability.");
        }
        this.durability = durability;
    }

    public ToolType type(){
        return type;
    }

    public void use(Breakable breakable){
        if(durability == 0) return;

        if(checkToolAndBreakableMatch(breakable)){
            breakable.damage(efficiency);
            durability--;
        }
        // strength
        // handle monsters with sword.
    }

    private boolean checkToolAndBreakableMatch(Breakable b){
        return b.getType() == BreakableType.TREE && type == ToolType.AXE ||
                b.getType() == BreakableType.ROCK && type == ToolType.PICKAXE ||
                b.getType() == BreakableType.SAND && type == ToolType.SHOVEL;
    }

    public Tool copy(){
        return new Tool(super.name(), efficiency, maxDurability, durability, type);
    }


    @Override
    public boolean equals(Object other){
        if(!(other instanceof Tool))
            return false;
        if(!super.equals(other))
            return false;
        Tool tool = (Tool) other;
        return efficiency == tool.efficiency && maxDurability == tool.maxDurability &&
                durability == tool.durability && type == tool.type;
    }

    @Override
    public int hashCode(){
        int result = super.hashCode();
        result = result * 31 + efficiency;
        result = result * 31 + maxDurability;
        result = result * 31 + durability;
        result = result * 31 + type.hashCode();
        return result;
    }
}
