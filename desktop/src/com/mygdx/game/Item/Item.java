package com.mygdx.game.Item;

import java.util.Objects;

public class Item {

    private final int itemId;
    private final String name;
    private final int stackSize;
    private int amount;

    public Item(Builder builder){
        this.itemId = builder.itemId;
        this.name = builder.name;
        this.stackSize = builder.stackSize;
        this.amount = builder.amount;
    }


    public final int getItemId(){
        return itemId;
    }

    public final String getName(){
        return name;
    }

    public final int getStackSize(){
        return stackSize;
    }

    public final int getAmount(){
        return amount;
    }

    public final void setAmount(int amount){
        if(amount < 0 || amount > stackSize){
            throw new IllegalArgumentException("Cannot set the item amount to a negative");
        }
        this.amount = amount;
    }


    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Item)){
            return false;
        }
        Item item = (Item) o;
        return itemId == item.itemId && name.equals(item.name) && stackSize == item.stackSize && amount == item.amount;
    }

    @Override
    public int hashCode(){
        return Objects.hash(itemId, name, stackSize, amount);
    }


    public static Builder itemBuilder(){
        return new Builder();
    }

    public static class Builder{

        private int itemId = -1;
        private String name;
        private int stackSize;
        private int amount = -1;

        private Builder(){
        }

        public Builder itemId(int itemId){
            this.itemId = itemId;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder stackSize(int stackSize){
            this.stackSize = stackSize;
            return this;
        }

        public Builder amount(int amount){
            this.amount = amount;
            return this;
        }

        public Item build(){
            if(itemId < 0){
                throw new IllegalArgumentException("The id of an item cannot be negative");
            }
            Objects.requireNonNull(name, "The name of an item must not be null");
            if(stackSize <= 0){
                throw new IllegalArgumentException("The stack size of an item must be strictly positive");
            }
            if(amount <= 0){
                throw new IllegalArgumentException("The amount of an item must be strictly positive");
            }
            return new Item(this);
        }
    }
}
