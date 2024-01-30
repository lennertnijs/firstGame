package com.mygdx.game.Item;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

public class Item {
    private final String name;
    private final int stackSize;
    private final Texture texture;

    public Item(Builder builder){
        this.name = builder.name;
        this.stackSize = builder.stackSize;
        this.texture = builder.texture;
    }

    public final int getStackSize(){
        return stackSize;
    }

    public final String getName(){
        return name;
    }


    public static Builder itemBuilder(){
        return new Builder();
    }

    public static class Builder{

        private String name;
        private int stackSize;
        private Texture texture;

        public Builder(){

        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder stackSize(int stackSize){
            this.stackSize = stackSize;
            return this;
        }

        public Builder texture(Texture texture){
            this.texture = texture;
            return this;
        }

        public Item build(){
            Objects.requireNonNull(name, "The name of an item must not be null");
            if(stackSize <= 0){
                throw new IllegalArgumentException("The stack size of an item must be strictly positive");
            }
            return new Item(this);
        }
    }
}
