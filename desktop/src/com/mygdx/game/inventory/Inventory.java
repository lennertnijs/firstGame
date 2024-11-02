package com.mygdx.game.inventory;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A mutable inventory that stores a predefined amount of items.
 * An inventory contains:
 * - an array of items (and nulls for empty slots)
 * - a map of stack sizes
 */
public final class Inventory {

    private final Item[] items;
    private final Map<String, Integer> stackSizeMap;

    public Inventory(Item[] items, Map<String, Integer> stackSizeMap){
        Objects.requireNonNull(items, "Item array cannot be null.");
        if(items.length == 0) {
            throw new IllegalArgumentException("An inventory cannot have 0 slots.");
        }
        this.items = items;
        Objects.requireNonNull(stackSizeMap, "Stack size map cannot be null.");
        if(stackSizeMap.containsKey(null)){
            throw new IllegalArgumentException("Map cannot contain a null key.");
        }
        if(stackSizeMap.containsValue(null)){
            throw new IllegalArgumentException("Map cannot contain a null value.");
        }
        this.stackSizeMap = stackSizeMap;
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
    public boolean contains(String name, int amount){
        Objects.requireNonNull(name, "Item name cannot be null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount cannot be negative or 0.");
        }
        int count = 0;
        for(Item stack : items){
            if(stack == null) continue;

            if(count >= amount){
                return true;
            }
            if(stack.name().equals(name)){
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
    public int add(String name, int amount){
        Objects.requireNonNull(name, "Item name cannot be null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount cannot be negative or 0.");
        }
        if(!stackSizeMap.containsKey(name)){
            throw new NoSuchElementException("No stack size is registered for this name.");
        }
        int stackSize = stackSizeMap.get(name);
        // fill half-full slots
        int index = findIndexOfSlotNotFullyFilled(name);
        while(index != -1 && amount > 0) {
            amount = items[index].increaseAmount(amount, stackSize);
            index = findIndexOfSlotNotFullyFilled(name);
        }
        // fill empty slots
        int indexOfEmpty = findIndexOfEmptySlot();
        while(indexOfEmpty != -1 && amount > 0) {
            items[indexOfEmpty] = new Item(name, Math.min(amount, stackSize));
            amount -= Math.min(amount, stackSize);
            indexOfEmpty = findIndexOfEmptySlot();
        }
        return amount;
    }

    private int findIndexOfSlotNotFullyFilled(String name){
        for (int i = 0; i < items.length; i++) {
            if(items[i] == null) continue;
            if(items[i].getAmount() == stackSizeMap.get(name)) continue;
            if(!items[i].name().equals(name)) continue;
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
    public void remove(String name, int amount){
        Objects.requireNonNull(name, "Item name cannot be null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount cannot be negative or 0.");
        }
        if(!contains(name, amount)){
            throw new IllegalArgumentException("Cannot remove that amount, because the inventory does not contain it.");
        }
        // remove the items
        for(Item item : items){
            if(item == null) continue;
            if(amount == 0) return;

            if(item.name().equals(name)){
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

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("Inventory[" + System.lineSeparator());
        for(int i = 0; i < items.length; i++){
            builder.append("Slot ").append(i).append(": ").append(items[i]).append(System.lineSeparator());
        }
        builder.append("]");
        return builder.toString();
    }
}
