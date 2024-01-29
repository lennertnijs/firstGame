package com.mygdx.game.Item;

import java.util.Objects;

public class Item {
    private final String name;
    private final String spritePath;
    private final int stackSize;

    public Item(Builder builder){
        this.name = builder.name;
        this.spritePath = builder.spritePath;
        this.stackSize = builder.stackSize;
    }

    public final int getStackSize(){
        return stackSize;
    }

    public final String getName(){
        return name;
    }

    public final String getSpritePath(){
        return spritePath;
    }

    public static Builder itemBuilder(){
        return new Builder();
    }

    public static class Builder{

        private String name;
        private String spritePath;
        private int stackSize;

        public Builder(){

        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder spritePath(String spritePath){
            this.spritePath = spritePath;
            return this;
        }

        public Builder stackSize(int stackSize){
            this.stackSize = stackSize;
            return this;
        }

        public Item build(){
            Objects.requireNonNull(name, "The name of an item must not be null");
            Objects.requireNonNull(spritePath, "The spritePath of an item must not be null");
            if(stackSize <= 0){
                throw new IllegalArgumentException("The stack size of an item must be strictly positive");
            }
            return new Item(this);
        }
    }
}
