package com.mygdx.game.Inventory;

import java.util.Objects;

/**
 * Represents a game inventory.
 * An inventory holds items in stacks.
 */
public final class Inventory {

    /**
     * The item stacks of the inventory.
     * Null entries imply an empty slot.
     */
    private final ItemStack[] stacks;

    private Inventory(ItemStack[] stacks){
        this.stacks = stacks;
    }

    /**
     * Creates a new MUTABLE {@link Inventory} with the given item stacks.
     * Deep copies each item stack, so the resulting inventory will be independent of the passed array.
     * @param stacks The item stacks. Cannot be null. Cannot be of length 0. CAN CONTAIN NULL for empty slots
     *
     * @return The inventory.
     */
    public static Inventory createWithStacks(ItemStack[] stacks){
        Objects.requireNonNull(stacks, "List is null.");
        if(stacks.length == 0) {
            throw new IllegalArgumentException("Length of the array is 0.");
        }
        ItemStack[] copy = new ItemStack[stacks.length];
        for(int i = 0; i < stacks.length; i++){
            copy[i] = (stacks[i] == null) ? null : stacks[i].copy();
        }
        return new Inventory(copy);
    }

    /**
     * Creates a new MUTABLE {@link Inventory} of the given size, with all slots empty (null).
     * @param size The size. Cannot be negative or 0.
     *
     * @return The inventory.
     */
    public static Inventory createEmptyOfSize(int size){
        if(size <= 0) {
            throw new IllegalArgumentException("Size is negative or 0.");
        }
        return new Inventory(new ItemStack[size]);
    }

    /**
     * @return The size. (amount of slots)
     */
    public int size(){
        return stacks.length;
    }

    /**
     * @return A deep copy of the item stacks.
     */
    public ItemStack[] getItems(){
        ItemStack[] copy = new ItemStack[stacks.length];
        for(int i = 0; i < stacks.length; i++){
            copy[i] = (stacks[i] == null) ? null : stacks[i].copy();
        }
        return copy;
    }

    /**
     * Fetches and returns the item stack at the given index.
     * @param index The index. 0 <= index < inventory.size()
     *
     * @return The item stack. (Null, if the slot is empty)
     */
    public ItemStack getItem(int index){
        if(index < 0){
            throw new IllegalArgumentException("Index is negative.");
        }
        if(index >= stacks.length){
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        return (stacks[index] == null) ? null : stacks[index].copy();
    }

    /**
     * Checks whether the inventory contains the given item for at least the given amount.
     * @param item The item. Cannot be null.
     * @param amount The amount. Amount > 0
     *
     * @return True if the inventory contains that amount of the item. False otherwise.
     */
    public boolean contains(Item item, int amount){
        Objects.requireNonNull(item, "Name is null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or 0.");
        }
        int count = 0;
        for(ItemStack stack : stacks){
            if(stack == null) continue;

            if(count >= amount){
                return true;
            }
            if(stack.item().equals(item)){
                count += stack.getAmount();
            }
        }
        return count >= amount;
    }

    /**
     * Adds the given item with the given amount to the inventory, using the given maximum stack size.
     * First fills the non-full slots that contain the same item. Then fills empty slots.
     * @param item The item. Cannot be null.
     * @param amount The amount. Amount > 0
     * @param stackSize The stack size. Stack size > 0
     *
     * @return The amount that could not be added to the inventory.
     */
    public int add(Item item, int amount, int stackSize){
        Objects.requireNonNull(item, "Item stack is null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or 0.");
        }
        if(stackSize <= 0){
            throw new IllegalArgumentException("Stack size is negative or 0.");
        }

        int index = findIndexOfSlotNotFullyFilled(item);
        while(index != -1 && amount > 0) {
            amount = stacks[index].increaseAmount(amount);
            index = findIndexOfSlotNotFullyFilled(item);
        }

        int indexOfEmpty = findIndexOfEmptySlot();
        while(indexOfEmpty != -1 && amount > 0) {
            stacks[indexOfEmpty] = new ItemStack(item, Math.min(amount, stackSize), stackSize);
            amount -= Math.min(amount, stackSize);
            indexOfEmpty = findIndexOfEmptySlot();
        }

        return amount;
    }

    /**
     * Removes the given amount of the given item from the inventory.
     * When a slot is emptied out (amount set to 0), it's replaced by null.
     * @param item The item. Cannot be null.
     * @param amount The amount. Amount > 0
     *
     * @throws IllegalArgumentException If the inventory does not contain at least that amount of the given item.
     */
    public void remove(Item item, int amount){
        Objects.requireNonNull(item, "Name is null");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or 0.");
        }
        if(!contains(item, amount)){
            throw new IllegalArgumentException("Cannot remove that amount, because the inventory does not contain it.");
        }
        for(ItemStack stack : stacks){
            if(stack == null) continue;
            if(amount == 0) return;

            if(stack.item().equals(item)){
                amount = stack.decreaseAmount(amount);
            }
        }
        replaceEmptyStacksWithNull();
    }

    /**
     * Finds the index of the first slot in the inventory that contains the given item, and is not yet full.
     * @param item The item. Cannot be null.
     *
     * @return The index, if any slot found. -1 if no such slot was found.
     */
    private int findIndexOfSlotNotFullyFilled(Item item){
        for (int i = 0; i < stacks.length; i++) {
            if(stacks[i] == null) continue;
            if(stacks[i].isFull()) continue;
            if(!stacks[i].item().equals(item)) continue;

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
        for(int i = 0; i < stacks.length; i++){
            if(stacks[i] != null) continue;

            return i;
        }
        return -1;
    }

    /**
     * Replaces any item stacks with an amount of 0 in the inventory by null.
     */
    private void replaceEmptyStacksWithNull(){
        for(int i = 0; i < stacks.length; i++){
            if(stacks[i] == null) continue;
            if(stacks[i].getAmount() == 0){
                stacks[i] = null;
            }
        }
    }

    /**
     * @return The string representation.
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder("Inventory[" + System.lineSeparator());
        for(int i = 0; i < stacks.length; i++){
            builder.append("Slot ").append(i).append(": ").append(stacks[i]).append(System.lineSeparator());
        }
        builder.append("]");
        return builder.toString();
    }
}
