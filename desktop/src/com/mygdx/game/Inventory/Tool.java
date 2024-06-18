package com.mygdx.game.Inventory;

import java.util.Objects;

/**
 * Tool item. Has an efficiency, durability and max durability. Also holds a type (pickaxe, axe, ...)
 */
public final class Tool extends Item {

    /**
     * The tool's efficiency.
     */
    private final int efficiency;
    /**
     * The tool's current durability.
     */
    private int durability;
    /**
     * The tool's maximum durability.
     */
    private final int maxDurability;
    /**
     * The tool's type.
     */
    private final ToolType type;

    private Tool(Builder builder){
        super(builder.name);
        this.efficiency = builder.efficiency;
        this.maxDurability = builder.maxDurability;
        this.durability = builder.durability;
        this.type = builder.toolType;
    }

    /**
     * @return The efficiency.
     */
    public int efficiency(){
        return efficiency;
    }

    /**
     * @return The current durability.
     */
    public int getDurability(){
        return durability;
    }

    /**
     * Sets the durability of this tool to the given amount.
     * @param durability The new durability. Cannot be negative or bigger than maximum durability.
     */
    public void setDurability(int durability){
        if(durability < 0){
            throw new IllegalArgumentException("Durability cannot be negative.");
        }
        if(durability > maxDurability){
            throw new IllegalArgumentException("Durability cannot exceed max durability.");
        }
        this.durability = durability;
    }

    /**
     * @return The maximum durability.
     */
    public int maxDurability(){
        return maxDurability;
    }

    /**
     * @return The tool type.
     */
    public ToolType type(){
        return type;
    }

    /**
     * @return A deep copy of this tool.
     */
    @Override
    public Tool copy(){
        return new Builder()
                .name(super.name())
                .efficiency(efficiency)
                .durability(durability)
                .maxDurability(maxDurability)
                .toolType(type).build();
    }

    /**
     * Compares two tool objects and returns true if they're equal. Returns false otherwise.
     *
     * @return True if equal, false otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Tool))
            return false;
        if(!super.equals(other))
            return false;
        Tool tool = (Tool) other;
        return efficiency == tool.efficiency && durability == tool.durability
                && maxDurability == tool.maxDurability && type == tool.type;
    }

    /**
     * @return The hash code.
     */
    @Override
    public int hashCode(){
        int result = super.hashCode();
        result = result * 31 + efficiency;
        result = result * 31 + maxDurability;
        result = result * 31 + durability;
        result = result * 31 + type.hashCode();
        return result;
    }

    /**
     * @return The string representation of this tool.
     */
    @Override
    public String toString(){
        return String.format("Tool[name=%s, efficiency=%d, durability=%d, maxDurability=%d, type=%s]",
                super.name(), efficiency, durability, maxDurability, type);
    }

    /**
     * @return A new builder to build a tool with.
     */
    public static Builder builder(){
        return new Builder();
    }

    public final static class Builder{

        private String name = null;
        private int efficiency = -1;
        private int durability = -1;
        private int maxDurability = -1;
        private ToolType toolType = null;

        private Builder(){
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder efficiency(int efficiency){
            this.efficiency = efficiency;
            return this;
        }

        public Builder durability(int durability){
            this.durability = durability;
            return this;
        }

        public Builder maxDurability(int maxDurability){
            this.maxDurability = maxDurability;
            return this;
        }

        public Builder toolType(ToolType toolType){
            this.toolType = toolType;
            return this;
        }

        public Tool build(){
            if(efficiency < 0) {
                throw new IllegalArgumentException("Efficiency is negative.");
            }
            if(maxDurability <= 0){
                throw new IllegalArgumentException("Max durability is negative or zero.");
            }
            if(durability < 0) {
                this.durability = maxDurability; // full durability
            }
            if(durability > maxDurability){
                throw new IllegalArgumentException("Durability is bigger than the max durability.");
            }
            Objects.requireNonNull(toolType, "Tool type is null.");
            return new Tool(this);
        }
    }
}
