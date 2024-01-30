package com.mygdx.game.Item;

import java.util.Objects;

public class ToolInstance extends ItemInstance {
    private int durability;
    public ToolInstance(Builder builder){
        super(ItemInstance.builder().item(builder.item).amount(builder.amount));
        this.durability = builder.durability;
    }

    public int getDurability(){
        return durability;
    }

    public void use(){
        this.durability -= 1;
    }

    public static Builder toolBuilder(){
        return new Builder();
    }

    public static class Builder{

        private Item item;
        private int amount;
        private int durability;


        public Builder item(Item item){
            this.item = item;
            return this;
        }

        public Builder amount(int amount){
            this.amount = amount;
            return this;
        }

        public Builder durability(int durability){
            this.durability = durability;
            return this;
        }

        public ToolInstance build(){
            Objects.requireNonNull(item, "The item of an tool instance cannot be null");
            if(amount <= 0 || amount > item.getStackSize()){
                throw new IllegalArgumentException("The amount of a tool instance has to be positive and within stack size range");
            }
            if(durability < 0){
                throw new IllegalArgumentException("The durability of a tool instance must not be negative");
            }
            return new ToolInstance(this);
        }
    }
}
