package com.mygdx.game.Item;

import java.util.Objects;

public class Item {

    private final int id;
    private final int stackSize;
    private final String name;
    private final String spritePath;

    public Item(Builder builder){
        this.id = builder.id;
        this.stackSize = builder.stackSize;
        this.name = builder.name;
        this.spritePath = builder.spritePath;
    }

    public int getId(){
        return id;
    }

    public int getStackSize(){
        return stackSize;
    }

    public String getName(){
        return name;
    }

    public String getSpritePath(){
        return spritePath;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private int id;
        private int stackSize;
        private String name;
        private String spritePath;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder stackSize(int stackSize){
            this.stackSize = stackSize;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder spritePath(String spritePath){
            this.spritePath = spritePath;
            return this;
        }

        public Item build(){
            if(id < 0){
                throw new IllegalArgumentException("The id of an item must not be negative");
            }
            if(stackSize <= 0){
                throw new IllegalArgumentException("The stack size of an item must be positive");
            }
            Objects.requireNonNull(name, "The name of an item must not be null");
            Objects.requireNonNull(spritePath, "The sprite path of an item must not be null");
            return new Item(this);
        }
    }
}
