package com.mygdx.game.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Represents a game inventory.
 * An inventory holds items in items.
 */
public final class Inventory {

    /**
     * The item items of the inventory.
     * Null entries imply an empty slot.
     */
    private final Item[] items;
    private final Map<String, Integer> stackSizeMap;

    private Inventory(Item[] items, Map<String, Integer> stackSizeMap){
        this.items = items;
        this.stackSizeMap = stackSizeMap;
    }

    /**
     * Creates a new MUTABLE {@link Inventory} with the given item items.
     * Deep copies each item stack, so the resulting inventory will be independent of the passed array.
     * @param items The item items. Cannot be null. Cannot be of length 0. CAN CONTAIN NULL for empty slots
     *
     * @return The inventory.
     */
    public static Inventory createWithItems(Item[] items, Map<String, Integer> stackSizeMap){
        Objects.requireNonNull(items, "List is null.");
        if(items.length == 0) {
            throw new IllegalArgumentException("Length of the array is 0.");
        }
        Item[] copy = new Item[items.length];
        for(int i = 0; i < items.length; i++){
            copy[i] = (items[i] == null) ? null : items[i].copy();
        }
        return new Inventory(copy, validateAndCopyMap(stackSizeMap));
    }

    /**
     * Creates a new MUTABLE {@link Inventory} of the given size, with all slots empty (null).
     * @param size The size. Cannot be negative or 0.
     *
     * @return The inventory.
     */
    public static Inventory createEmptyOfSize(int size, Map<String, Integer> stackSizeMap){
        if(size <= 0) {
            throw new IllegalArgumentException("Size is negative or 0.");
        }
        return new Inventory(new Item[size], validateAndCopyMap(stackSizeMap));
    }

    private static Map<String, Integer> validateAndCopyMap(Map<String, Integer> map){
        Objects.requireNonNull(map, "Map is null.");
        if(map.containsValue(null) || map.containsKey(null)){
            throw new IllegalArgumentException("Map contains a null key or value.");
        }
        return new HashMap<>(map);
    }

    /**
     * @return The size. (amount of slots)
     */
    public int size(){
        return items.length;
    }

    /**
     * @return A deep copy of the item items.
     */
    public Item[] getItems(){
        Item[] copy = new Item[items.length];
        for(int i = 0; i < items.length; i++){
            copy[i] = (items[i] == null) ? null : items[i].copy();
        }
        return copy;
    }

    /**
     * Fetches and returns the item stack at the given index.
     * @param index The index. 0 <= index < inventory.size()
     *
     * @return The item stack. (Null, if the slot is empty)
     */
    public Item getItem(int index){
        if(index < 0){
            throw new IllegalArgumentException("Index is negative.");
        }
        if(index >= items.length){
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        return (items[index] == null) ? null : items[index].copy();
    }

    /**
     * Checks whether the inventory contains the given item for at least the given amount.
     * @param name The item. Cannot be null.
     * @param amount The amount. Amount > 0
     *
     * @return True if the inventory contains that amount of the item. False otherwise.
     */
    public boolean contains(String name, int amount){
        Objects.requireNonNull(name, "Name is null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or 0.");
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
     * Adds the given item with the given amount to the inventory, using the given maximum stack size.
     * First fills the non-full slots that contain the same item. Then fills empty slots.
     * @param name The item name. Cannot be null.
     * @param amount The amount. Amount > 0
     *
     * @return The amount that could not be added to the inventory.
     */
    public int add(String name, int amount){
        Objects.requireNonNull(name, "Item stack is null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or 0.");
        }
        if(!stackSizeMap.containsKey(name)){
            throw new NoSuchElementException("No stack size is registered for this name.");
        }
        int stackSize = stackSizeMap.get(name);

        int index = findIndexOfSlotNotFullyFilled(name);
        while(index != -1 && amount > 0) {
            amount = items[index].increaseAmount(amount, stackSize);
            index = findIndexOfSlotNotFullyFilled(name);
        }

        int indexOfEmpty = findIndexOfEmptySlot();
        while(indexOfEmpty != -1 && amount > 0) {
            items[indexOfEmpty] = new Item(name, Math.min(amount, stackSize));
            amount -= Math.min(amount, stackSize);
            indexOfEmpty = findIndexOfEmptySlot();
        }

        return amount;
    }

    /**
     * Removes the given amount of the given item from the inventory.
     * When a slot is emptied out (amount set to 0), it's replaced by null.
     * @param name The item name. Cannot be null.
     * @param amount The amount. Amount > 0
     *
     * @throws IllegalArgumentException If the inventory does not contain at least that amount of the given item.
     */
    public void remove(String name, int amount){
        Objects.requireNonNull(name, "Name is null");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or 0.");
        }
        if(!contains(name, amount)){
            throw new IllegalArgumentException("Cannot remove that amount, because the inventory does not contain it.");
        }
        
        for(Item stack : items){
            if(stack == null) continue;
            if(amount == 0) return;

            if(stack.name().equals(name)){
                amount = stack.decreaseAmount(amount);
            }
        }
        replaceEmptyItemsWithNull();
    }

    /**
     * Finds the index of the first slot in the inventory that contains the given item, and is not yet full.
     * @param name The item. Cannot be null.
     *
     * @return The index, if any slot found. -1 if no such slot was found.
     */
    private int findIndexOfSlotNotFullyFilled(String name){
        for (int i = 0; i < items.length; i++) {
            if(items[i] == null) continue;
            if(items[i].getAmount() == stackSizeMap.get(name)) continue;
            if(!items[i].name().equals(name)) continue;

            return i;
        }
        return -1;
    }

    /**
     * Finds the index of the first slot in the inventory that is empty (so, is null).
     *
     * @return The index, if any slot was found. -1 if no such slot was found.
     */
    private int findIndexOfEmptySlot(){
        for(int i = 0; i < items.length; i++){
            if(items[i] != null) continue;

            return i;
        }
        return -1;
    }

    /**
     * Replaces any item items with an amount of 0 in the inventory by null.
     */
    private void replaceEmptyItemsWithNull(){
        for(int i = 0; i < items.length; i++){
            if(items[i] == null) continue;
            if(items[i].getAmount() == 0){
                items[i] = null;
            }
        }
    }

    /**
     * @return The string representation.
     */
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
