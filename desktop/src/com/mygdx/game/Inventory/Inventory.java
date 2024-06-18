package com.mygdx.game.Inventory;

import java.util.Arrays;
import java.util.Objects;

public final class Inventory {

    private final ItemStack[] itemStacks;

    public Inventory(int size){
        if(size <= 0) {
            throw new IllegalArgumentException("Size is negative or 0.");
        }
        itemStacks = new ItemStack[size];
    }

    public Inventory(ItemStack[] itemStacks){
        Objects.requireNonNull(itemStacks, "List is null.");
        if(Arrays.stream(itemStacks).anyMatch(Objects::isNull)){
            throw new NullPointerException("List contains null.");
        }
        if(itemStacks.length == 0) {
            throw new IllegalArgumentException("Length of the array is 0.");
        }
        this.itemStacks = new ItemStack[itemStacks.length];
        for(int i = 0; i < itemStacks.length; i++){
            this.itemStacks[i] = itemStacks[i].copy();
        }
    }

    public ItemStack[] getItems(){
        return Arrays.copyOf(itemStacks, itemStacks.length);
    }

    public ItemStack getActiveItem(int index){
        return itemStacks[index];
    }

    public boolean contains(String name, int amount){
        Objects.requireNonNull(name, "Name is null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or 0.");
        }
        int count = 0;
        for(ItemStack itemStack : itemStacks){
            if(count >= amount){
                return true;
            }
            if(itemStack.item().name().equals(name)){
                count += itemStack.getAmount();
            }
        }
        return count >= amount;
    }

    /**
     * First fills the non-full slots that contain the same item. Then fills empty slots.
     *
     * @return The amount that could not be added to the inventory.
     */
    public int addItem(ItemStack itemStack){
        Objects.requireNonNull(itemStack, "Item is null.");
        String name = itemStack.item().name();
        int amount = itemStack.getAmount();

        int index = findIndexOfSlotNotFullyFilled(name);
        while(index != -1 && amount > 0) {
            amount = itemStacks[index].increaseAmount(amount);
            index = findIndexOfSlotNotFullyFilled(name);
        }

        int indexOfEmpty = findIndexOfEmptySlot();
        while(indexOfEmpty != -1 && amount > 0) {
            int itemAmount = Math.min(amount, itemStack.stackSize());
            itemStacks[indexOfEmpty] = new ItemStack(itemStack.item(), itemAmount, itemStack.stackSize());
            amount -= itemAmount;
        }

        return amount;
    }

    public void remove(String name, int amount){
        Objects.requireNonNull(name, "Name is null");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or 0.");
        }
        if(!contains(name, amount)){
            throw new IllegalStateException("Cannot remove that amount, because the inventory does not contain it.");
        }
        for(ItemStack itemStack : itemStacks){
            if(amount == 0){
                return;
            }
            if(itemStack.item().name().equals(name)){
                amount = itemStack.decreaseAmount(amount);
            }
        }
        replaceEmptyItemsWithNull();
    }



    private int findIndexOfEmptySlot(){
        for(int i = 0; i < itemStacks.length; i++){
            if(itemStacks[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int findIndexOfSlotNotFullyFilled(String name){
        for (int i = 0; i < itemStacks.length; i++) {
            if(itemStacks[i] != null && itemStacks[i].item().name().equals(name) && itemStacks[i].getAmount() < itemStacks[i].stackSize()) {
                return i;
            }
        }
        return -1;
    }

    private void replaceEmptyItemsWithNull(){
        for(int i = 0; i < itemStacks.length; i++){
            if(itemStacks[i].getAmount() == 0) {
                itemStacks[i] = null;
            }
        }
    }

    public Inventory copy(){
        return new Inventory(itemStacks);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Inventory)){
            return false;
        }
        Inventory inventory = (Inventory) other;
        return Arrays.equals(itemStacks, inventory.itemStacks);
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(itemStacks);
    }

    @Override
    public String toString(){
        return String.format("Inventory{Items=%s}", Arrays.toString(itemStacks));
    }
}
