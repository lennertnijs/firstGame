package com.mygdx.game.Player;

import com.mygdx.game.Item.Item;

import java.util.Objects;

public class Inventory {
    private final int size;
    private final Item[] items;

    public Inventory(Builder builder){
        this.size = builder.size;
        this.items = builder.items;
    }

    public int getSize(){
        return size;
    }

    public Item[] getItems(){
        return items;
    }

    public boolean hasEmptySlot(){
        for (Item item : items) {
            if (item == null) {
                return true;
            }
        }
        return false;
    }

    public void addItem(Item item){
        Objects.requireNonNull(item, "Cannot add a null item to the inventory");
        if(!hasEmptySlot()){
            return;
        }
        for(int i = 0; i < items.length; i++){
            if(items[i] == null){
                items[i] = item;
                return;
            }
        }
    }


    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private int size;
        private Item[] items;

        public Builder(){

        }

        public Builder size(int size){
            this.size = size;
            return this;
        }

        public Builder items(Item[] items){
            this.items = items;
            return this;
        }

        public Inventory build(){
            if(size < 0){
                throw new IllegalArgumentException("The size of the inventory must not be negative or 0");
            }
            Objects.requireNonNull(items, "The item list of the inventory must not be null");
            if(items.length != size){
                throw new IllegalArgumentException("The size of the inventory does not match the amount of items");
            }
            return new Inventory(this);
        }
    }
}
