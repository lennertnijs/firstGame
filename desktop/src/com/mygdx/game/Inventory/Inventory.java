package com.mygdx.game.Inventory;

import java.util.Arrays;
import java.util.Objects;

public final class Inventory {

    private final ItemStack[] stacks;

    private Inventory(ItemStack[] stacks){
        this.stacks = stacks;
    }

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

    public static Inventory createEmptyOfSize(int size){
        if(size <= 0) {
            throw new IllegalArgumentException("Size is negative or 0.");
        }
        return new Inventory(new ItemStack[size]);
    }

    public int size(){
        return stacks.length;
    }

    public ItemStack[] getItems(){
        ItemStack[] copy = new ItemStack[stacks.length];
        for(int i = 0; i < stacks.length; i++){
            copy[i] = (stacks[i] == null) ? null : stacks[i].copy();
        }
        return copy;
    }

    public ItemStack getItem(int index){
        if(index < 0){
            throw new IllegalArgumentException("Index is negative.");
        }
        if(index >= stacks.length){
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        return stacks[index].copy();
    }

    public boolean contains(Item item, int amount){
        Objects.requireNonNull(item, "Name is null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or 0.");
        }
        int count = 0;
        for(ItemStack stack : stacks){
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
     * First fills the non-full slots that contain the same item. Then fills empty slots.
     *
     * @return The amount that could not be added to the inventory.
     */
    public int add(Item item, int amount, int stackSize){
        Objects.requireNonNull(item, "Item stack is null.");

        int index = findIndexOfSlotNotFullyFilled(item);
        while(index != -1 && amount > 0) {
            amount = stacks[index].increaseAmount(amount);
            index = findIndexOfSlotNotFullyFilled(item);
        }

        int indexOfEmpty = findIndexOfEmptySlot();
        while(indexOfEmpty != -1 && amount > 0) {
            stacks[indexOfEmpty] = new ItemStack(item, Math.min(amount, stackSize), stackSize);
            amount -= Math.min(amount, stackSize);
        }

        return amount;
    }

    public void remove(Item item, int amount){
        Objects.requireNonNull(item, "Name is null");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or 0.");
        }
        if(!contains(item, amount)){
            throw new IllegalStateException("Cannot remove that amount, because the inventory does not contain it.");
        }
        for(ItemStack stack : stacks){
            if(amount == 0){
                return;
            }
            if(stack.item().equals(item)){
                amount = stack.decreaseAmount(amount);
            }
        }
        replaceEmptyStacksWithNull();
    }



    private int findIndexOfEmptySlot(){
        for(int i = 0; i < stacks.length; i++){
            if(stacks[i] != null) continue;

            return i;
        }
        return -1;
    }

    private int findIndexOfSlotNotFullyFilled(Item item){
        for (int i = 0; i < stacks.length; i++) {
            if(stacks[i] == null) continue;
            if(stacks[i].isFull()) continue;
            if(!stacks[i].item().equals(item)) continue;

            return i;
        }
        return -1;
    }

    private void replaceEmptyStacksWithNull(){
        for(int i = 0; i < stacks.length; i++){
            if(stacks[i].getAmount() == 0) {
                stacks[i] = null;
            }
        }
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Inventory)){
            return false;
        }
        Inventory inventory = (Inventory) other;
        return Arrays.equals(stacks, inventory.stacks);
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(stacks);
    }

    @Override
    public String toString(){
        return String.format("Inventory{Items=%s}", Arrays.toString(stacks));
    }
}
