package com.mygdx.game.Player;

import com.mygdx.game.Item.Item;
import com.mygdx.game.Item.ItemInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Inventory {
    private final int size;
    private final List<ItemInstance> items;

    public Inventory(Builder builder){
        this.size = builder.size;
        this.items = builder.items;
    }

    public int getSize(){
        return size;
    }

    public List<ItemInstance> getItems(){
        return items;
    }

    public boolean contains(Item item){
        for(ItemInstance itemInstance : items){
            boolean foundItem = itemInstance.getItem().equals(item);
            if(foundItem){
                return true;
            }
        }
        return false;
    }

    public boolean containsAmount(Item item, int amount){
        Objects.requireNonNull(item, "The item to check the amount of must not be null");
        if(amount <= 0){
            throw new IllegalArgumentException("The amount of an item instance that is to be checked has to be positive");
        }
        for(ItemInstance itemInstance : items){
            boolean foundItem = itemInstance.getItem().equals(item);
            if(foundItem){
                boolean sufficientAmount = itemInstance.getAmount() >= amount;
                if(sufficientAmount){
                    return true;
                }
            }
        }
        return false;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private int size;
        private List<ItemInstance> items;

        public Builder(){

        }

        public Builder size(int size){
            this.size = size;
            return this;
        }

        public Builder items(List<ItemInstance> items){
            this.items = items;
            return this;
        }

        public Inventory build(){
            if(size < 0){
                throw new IllegalArgumentException("The size of the inventory must not be negative or 0");
            }
            Objects.requireNonNull(items, "The item list of the inventory must not be null");
            if(items.size() != size){
                throw new IllegalArgumentException("The size of the inventory does not match the inventory mapping");
            }
            for(ItemInstance item: items){
                Objects.requireNonNull(item, "The item instance of the inventory is not null");
            }
            return new Inventory(this);
        }
    }
}
