package com.mygdx.game.V2.Inventory;

import java.util.Arrays;
import java.util.Objects;

public final class Inventory {

    private final Item[] items;

    public Inventory(int size){
        if(size <= 0)
            throw new IllegalArgumentException("Size is negative or 0.");
        items = new Item[size];
    }

    public Inventory(Item[] items){
        Objects.requireNonNull(items, "List is null.");
        if(Arrays.stream(items).anyMatch(Objects::isNull))
            throw new NullPointerException("List contains null.");

        if(items.length == 0)
            throw new IllegalArgumentException("Length of the array is 0.");
        this.items = new Item[items.length];
        System.arraycopy(items, 0, this.items, 0, items.length);
    }


    public int addItem(Item item){
        Objects.requireNonNull(item, "Item is null.");
        String name = item.getName();
        int amount = item.getAmount();
        int index;

        while((index = findIndexOfSlotNotFullyFilled(name)) != -1 && amount > 0)
            amount = items[index].increaseAmount(amount);

        while((index = findIndexOfEmptySlot()) != -1 && amount > 0)
            items[index] = new Item(item.getTemplate(), amount);

        return amount;
    }

    private int findIndexOfEmptySlot(){
        for(int i = 0; i < items.length; i++){
            if(items[i] == null)
                return i;
        }
        return -1;
    }

    private int findIndexOfSlotNotFullyFilled(String name){
        for (int i = 0; i < items.length; i++)
            if(items[i] != null)
                if (items[i].getName().equals(name) && items[i].getAmount() < items[i].getStackSize())
                    return i;
        return -1;
    }
}
