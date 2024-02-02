package com.mygdx.game.Player;

import com.mygdx.game.Item.Item;

import java.util.Arrays;
import java.util.Objects;

public class Inventory {
    private final Item[] items;

    public Inventory(Builder builder){
        this.items = builder.items;
    }

    public Item[] getItems(){
        return items;
    }

    public int getSize(){
        return items.length;
    }

    public boolean hasEmptySlot(){
        for (Item item : items) {
            if (item == null) {
                return true;
            }
        }
        return false;
    }

    public Item getItem(int index){
        if(index < 0 || index >= items.length){
            throw new IllegalArgumentException("Cannot fetch an item because the index is out of inventory bounds");
        }
        return items[index];
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

    public void removeItem(int index){
        if(index < 0 || index >= items.length){
            throw new IllegalArgumentException("Cannot remove an item at an index outside the inventory size");
        }
        items[index] = null;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Inventory)){
            return false;
        }
        Inventory inventory = (Inventory) o;
        return Arrays.equals(items, inventory.getItems());
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(items);
    }


    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Item[] items;

        private Builder(){
        }

        public Builder items(Item[] items){
            this.items = items;
            return this;
        }

        public Inventory build(){
            Objects.requireNonNull(items, "The item list of the inventory must not be null");
            return new Inventory(this);
        }
    }
}
