package com.mygdx.game.Item;

public abstract class Item {
    private final int stackSize;
    private final String name;
    private final String spritePath;

    public Item(Builder<?> builder){
        if(builder.stackSize <= 0){
            throw new IllegalArgumentException("The stack size of an item must be strictly positive");
        }
        this.stackSize = builder.stackSize;
        this.name = builder.name;
        this.spritePath = builder.spritePath;
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


    abstract static class Builder<T extends Builder<T>>{
        private int stackSize;
        private String name;
        private String spritePath;

        public T stackSize(int stackSize){
            this.stackSize = stackSize;
            return self();
        }

        public T name(String name){
            this.name = name;
            return self();
        }

        public T spritePath(String spritePath){
            this.spritePath = spritePath;
            return self();
        }

        abstract Item build();

        protected T self(){
            return (T) this;
        }
    }
}
