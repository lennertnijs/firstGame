package com.mygdx.game.Item;

import java.util.Objects;

import static com.mygdx.game.Constants.TOOL_STACK_SIZE;

public class Tool extends Item{

    private final int efficiency;
    private int durability;
    private final ToolType toolType;

    public Tool(Builder builder){
        super(Item.itemBuilder().itemId(builder.itemId).name(builder.name).stackSize(TOOL_STACK_SIZE).amount(1));
        this.efficiency = builder.efficiency;
        this.durability = builder.durability;
        this.toolType = builder.toolType;
    }

    public final int getEfficiency(){
        return efficiency;
    }

    public final int getDurability(){
        return durability;
    }

    protected final void setDurability(int durability){
        if(durability < 0 || durability > this.durability){
            throw new IllegalArgumentException("Cannot set the durability to a negative value");
        }
        this.durability = durability;
    }

    public final ToolType getToolType(){
        return toolType;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Tool)){
            return false;
        }
        Tool tool = (Tool) o;
        return  getItemId() == tool.getItemId() &&
                getName().equals(tool.getName()) &&
                getStackSize() == tool.getStackSize() &&
                efficiency == tool.efficiency &&
                durability == tool.durability &&
                toolType.equals(tool.toolType);
    }

    @Override
    public int hashCode(){
        return Objects.hash(getItemId(), getName(), getStackSize(), efficiency, durability, toolType);
    }


    public static Builder toolBuilder(){
        return new Builder();
    }

    public static class Builder{

        // Item fields
        private int itemId = -1;
        private String name;

        // Tool fields
        private int efficiency;
        private int durability = -1;
        private ToolType toolType;


        public Builder itemId(int itemId){
            this.itemId = itemId;
            return this;
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

        public Builder toolType(ToolType toolType){
            this.toolType = toolType;
            return this;
        }

        public Tool build(){
            if(itemId < 0){
                throw new IllegalArgumentException("The id of a tool must not be negative");
            }
            Objects.requireNonNull(name, "The name of a tool must not be null");
            if(efficiency <= 0){
                throw new IllegalArgumentException("The efficiency of a tool has to be strictly positive");
            }
            if(durability < 0){
                throw new IllegalArgumentException("The durability of a tool must not be negative");
            }
            Objects.requireNonNull(toolType, "The type of a tool must not eb null");
            return new Tool(this);
        }
    }
}
