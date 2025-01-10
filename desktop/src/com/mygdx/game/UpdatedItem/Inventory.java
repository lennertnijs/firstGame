package com.mygdx.game.UpdatedItem;

import java.util.Objects;

/**
 * A mutable inventory that stores a predefined amount of items.
 * An inventory contains:
 * - an array of items (and nulls for empty slots)
 * - a map of stack sizes
 */
public final class Inventory {

    private final Item[] items;

    public Inventory(Item[] items){
        Objects.requireNonNull(items);
        if(items.length == 0) {
            throw new IllegalArgumentException("An inventory cannot have 0 slots.");
        }
        this.items = items;
    }

    public int size(){
        return items.length;
    }

    public Item[] getItems(){
        return items;
    }

    /**
     * Fetches and returns the item stack at the given index. Returns null if no item in the given slot index.
     */
    public Item getItem(int index){
        if(index < 0){
            throw new IllegalArgumentException("Index cannot be negative.");
        }
        if(index >= items.length){
            throw new IndexOutOfBoundsException("Index cannot be bigger than the size of the inventory.");
        }
        return items[index];
    }

    /**
     * Checks whether the inventory contains the given item for at least the given amount.
     */
    public boolean contains(ItemType type, int amount){
        Objects.requireNonNull(type, "Item name cannot be null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount cannot be negative or 0.");
        }
        int count = 0;
        for(Item stack : items){
            if(stack == null) continue;

            if(count >= amount){
                return true;
            }
            if(stack.type.equals(type)){
                count += stack.getAmount();
            }
        }
        return count >= amount;
    }

    /**
     * Adds the given item with the given amount to the inventory.
     * First fills the non-full slots that contain the same item. Then fills empty slots.
     * Returns the amount that could not be added to the inventory.
     */
    public int add(ItemType type, int amount){
        Objects.requireNonNull(type, "Item name cannot be null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount cannot be negative or 0.");
        }
        // fill half-full slots
        int index = findIndexOfSlotNotFullyFilled(type);
        while(index != -1 && amount > 0) {
            amount = items[index].increaseAmount(amount);
            index = findIndexOfSlotNotFullyFilled(type);
        }
        // fill empty slots
        int indexOfEmpty = findIndexOfEmptySlot();
        while(indexOfEmpty != -1 && amount > 0) {
            items[indexOfEmpty] = Item.builder().build(); // todo
            amount -= Math.min(amount, type.stackSize);
            indexOfEmpty = findIndexOfEmptySlot();
        }
        return amount;
    }

    private int findIndexOfSlotNotFullyFilled(ItemType type){
        for (int i = 0; i < items.length; i++) {
            if(items[i] == null) continue;
            if(!items[i].type.equals(type)) continue;
            if(items[i].getAmount() == type.stackSize) continue;
            return i;
        }
        return -1;
    }

    private int findIndexOfEmptySlot(){
        for(int i = 0; i < items.length; i++){
            if(items[i] != null) continue;

            return i;
        }
        return -1;
    }

    /**
     * Removes the given amount of the given item from the inventory.
     * When a slot is emptied out (amount set to 0), it's replaced by null.
     */
    public void remove(ItemType type, int amount){
        Objects.requireNonNull(type, "Item name cannot be null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount cannot be negative or 0.");
        }
        if(!contains(type, amount)){
            throw new IllegalArgumentException("Cannot remove that amount, because the inventory does not contain it.");
        }
        // remove the items
        for(Item item : items){
            if(item == null) continue;
            if(amount == 0) return;

            if(item.type.equals(type)){
                amount = item.decreaseAmount(amount);
            }
        }
        // replace empty stacks with null
        for(int i = 0; i < items.length; i++){
            if(items[i] == null) continue;
            if(items[i].getAmount() == 0){
                items[i] = null;
            }
        }
    }
}
