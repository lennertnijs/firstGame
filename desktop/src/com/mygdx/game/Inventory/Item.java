package com.mygdx.game.Inventory;

import java.util.Objects;

public class Item {

    private final String name;
    private final int maxStackSize;
    private int amount;

    public Item(String name, int maxStackSize, int amount){
        Objects.requireNonNull(name, "Name is null.");
        if(maxStackSize <= 0) {
            throw new IllegalArgumentException("Max stack size is negative or 0.");
        }
        if(amount <= 0 || amount > maxStackSize) {
            throw new IllegalArgumentException("Amount is negative, zero or bigger than the max stack size..");
        }
        this.name = name;
        this.maxStackSize = maxStackSize;
        this.amount = amount;
    }

    public String name(){
        return name;
    }

    public int maxStackSize(){
        return maxStackSize;
    }

    public int getAmount(){
        return amount;
    }

    /**
     * @return The amount of increase left.
     */
    public int increaseAmount(int increase){
        if(increase <= 0) {
            throw new IllegalArgumentException("Increase is negative or 0.");
        }
        int actualIncrease = Math.min(increase, maxStackSize - amount);
        this.amount += actualIncrease;
        return increase - actualIncrease;
    }

    /**
     * @return The amount of decrease left.
     */
    public int decreaseAmount(int decrease){
        if(decrease <= 0) {
            throw new IllegalArgumentException("Decrease is negative or 0.");
        }
        int actualDecrease = Math.min(decrease, amount);
        this.amount -= actualDecrease;
        return decrease - actualDecrease;
    }

    public Item copy(){
        return new Item(name, maxStackSize, amount);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Item)) {
            return false;
        }
        Item item = (Item) other;
        return name.equals(item.name) && maxStackSize == item.maxStackSize && amount == item.amount;
    }

    @Override
    public int hashCode(){
        int result = name.hashCode();
        result = result * 31 + maxStackSize;
        result = result * 31 + amount;
        return result;
    }

    @Override
    public String toString(){
        return String.format("Item[name=%s, maxStackSize=%d, amount=%d]", name, maxStackSize, amount);
    }
}
