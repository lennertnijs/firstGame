package com.mygdx.game.Player;

import com.mygdx.game.Item.Item;
import com.mygdx.game.Item.ItemInstance;

import java.util.HashMap;
import java.util.Objects;

public class Inventory {
    private final int size;
    private final HashMap<Integer, ItemInstance> inventory;

    public Inventory(Builder builder){
        this.size = builder.size;
        this.inventory = builder.inventory;
    }

    public int getSize(){
        return size;
    }

    public HashMap<Integer, ItemInstance> getInventory(){
        return inventory;
    }

    public boolean contains(Item item){
        for(ItemInstance itemInstance : inventory.values()){
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
        for(ItemInstance itemInstance : inventory.values()){
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
        private HashMap<Integer, ItemInstance> inventory;

        public Builder(){

        }

        public Builder size(int size){
            this.size = size;
            return this;
        }

        public Builder inventory(HashMap<Integer, ItemInstance> inventory){
            this.inventory = inventory;
            return this;
        }

        public Inventory build(){
            if(size <= 0){
                throw new IllegalArgumentException("The size of the inventory must not be negative or 0");
            }
            Objects.requireNonNull(inventory, "The inventory map of the inventory must not be null");
            if(inventory.size() != size){
                throw new IllegalArgumentException("The size of the inventory does not match the inventory mapping");
            }
            for(Integer i: inventory.keySet()){
                Objects.requireNonNull(i, "The id of the inventory slot must not be null");
            }
            return new Inventory(this);
        }
    }
}
