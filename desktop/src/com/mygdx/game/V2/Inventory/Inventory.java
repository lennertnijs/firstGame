package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.Breakables.Breakable;
import com.mygdx.game.V2.General.GameObject;

import java.util.Arrays;
import java.util.Objects;

public final class Inventory {

    private final Item[] items;

    public Inventory(int size){
        if(size <= 0) {
            throw new IllegalArgumentException("Size is negative or 0.");
        }
        items = new Item[size];
    }

    public Inventory(Item[] items){
        Objects.requireNonNull(items, "List is null.");
        if(Arrays.stream(items).anyMatch(Objects::isNull)){
            throw new NullPointerException("List contains null.");
        }
        if(items.length == 0) {
            throw new IllegalArgumentException("Length of the array is 0.");
        }
        this.items = new Item[items.length];
        for(int i = 0; i < items.length; i++){
            this.items[i] = items[i].copy();
        }
    }

    public Item[] getItems(){
        return Arrays.copyOf(items, items.length);
    }

    public boolean contains(String name, int amount){
        Objects.requireNonNull(name, "Name is null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or 0.");
        }
        int count = 0;
        for(Item item : items){
            if(count >= amount){
                return true;
            }
            if(item.getName().equals(name)){
                count += item.getAmount();
            }
        }
        return count >= amount;
    }

    /**
     * First fills the non-full slots that contain the same item. Then fills empty slots.
     *
     * @return The amount that could not be added to the inventory.
     */
    public int addItem(Item item){
        Objects.requireNonNull(item, "Item is null.");
        String name = item.getName();
        int amount = item.getAmount();

        int index = findIndexOfSlotNotFullyFilled(name);
        while(index != -1 && amount > 0) {
            amount = items[index].increaseAmount(amount);
            index = findIndexOfSlotNotFullyFilled(name);
        }

        int indexOfEmpty = findIndexOfEmptySlot();
        while(indexOfEmpty != -1 && amount > 0) {
            int itemAmount = Math.min(amount, item.getStackSize());
            items[indexOfEmpty] = new Item(name, item.getStackSize(), itemAmount);
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
        for(Item item : items){
            if(amount == 0){
                return;
            }
            if(item.getName().equals(name)){
                amount = item.decreaseAmount(amount);
            }
        }
        replaceEmptyItemsWithNull();
    }



    private int findIndexOfEmptySlot(){
        for(int i = 0; i < items.length; i++){
            if(items[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int findIndexOfSlotNotFullyFilled(String name){
        for (int i = 0; i < items.length; i++) {
            if(items[i] != null && items[i].getName().equals(name) && items[i].getAmount() < items[i].getStackSize()) {
                return i;
            }
        }
        return -1;
    }

    private void replaceEmptyItemsWithNull(){
        for(int i = 0; i < items.length; i++){
            if(items[i].amountIsZero()) {
                items[i] = null;
            }
        }
    }

    public void use(int index, GameObject object){
        if(index < 0 || index >= items.length){
            throw new IndexOutOfBoundsException("Index is out of inventory bounds.");
        }
        Item item = items[index];
        if(item instanceof Tool && object instanceof Breakable){
            Tool tool = (Tool) item;
            Breakable breakable = (Breakable) object;
            tool.use(breakable);
        }
    }

    public Inventory copy(){
        return new Inventory(items);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Inventory)){
            return false;
        }
        Inventory inventory = (Inventory) other;
        return Arrays.equals(items, inventory.items);
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(items);
    }

    @Override
    public String toString(){
        return String.format("Inventory{Items=%s}", Arrays.toString(items));
    }
}
