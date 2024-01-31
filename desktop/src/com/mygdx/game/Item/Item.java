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

    public final String getName(){
        return name;
    }


    public final int getStackSize(){
        return stackSize;
    }

    public final Texture getTexture(){
        return texture;
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
        return name.equals(item.name) && stackSize == item.stackSize;
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, stackSize, texture);
    }


    public static Builder itemBuilder(){
        return new Builder();
    }

    public static class Builder{

        private String name;
        private int stackSize;
        private Texture texture;

        private Builder(){
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
