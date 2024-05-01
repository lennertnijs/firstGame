package com.mygdx.game.V2.Inventory;

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
        this.items = new Item[items.length];
        System.arraycopy(items, 0, this.items, 0, items.length);
    }


    public int addItem(String name, int amount){
        int index;

        while((index = findIndexOfSlotNotFullyFilled(name)) != -1 && amount > 0)
            amount = items[index].increaseAmount(amount);

        while((index = findIndexOfEmptySlot()) != -1 && amount > 0)
            // create item and store it, as a replacent of the null.
            items[index] = new Item(amount, new ItemTemplate("temp", "temp", null, 64));

        return amount;
    }

    private int findNextIndexToFill(String name){
        int slotToFillIndex = findIndexOfSlotNotFullyFilled(name);
        if(slotToFillIndex != -1)
            return slotToFillIndex;
        return findIndexOfEmptySlot();
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
            if (items[i].getTemplate().name().equals(name))
                if (items[i].getAmount() < items[i].getTemplate().maxStackSize())
                    return i;
        return -1;
    }
}
