package com.mygdx.game.Inventory;

import java.util.Objects;

public class Item {

    private final String name;
    private int amount;
    private final int stackSize;

    public Item(String name, int amount, int stackSize){
        Objects.requireNonNull(name, "Name is null.");
        if(stackSize <= 0) {
            throw new IllegalArgumentException("Stack size is negative or 0.");
        }
        if(amount <= 0 || amount > stackSize) {
            throw new IllegalArgumentException("Amount is negative, zero or bigger than the max stack size..");
        }
        this.name = name;
        this.amount = amount;
        this.stackSize = stackSize;
    }

    public final String name(){
        return name;
    }

    public final int getAmount(){
        return amount;
    }

    public final int stackSize(){
        return stackSize;
    }

    /**
     * @return The amount of increase left.
     */
    public final int increaseAmount(int increase){
        if(increase <= 0) {
            throw new IllegalArgumentException("Increase is negative or 0.");
        }
        int actualIncrease = Math.min(increase, stackSize - amount);
        this.amount += actualIncrease;
        return increase - actualIncrease;
    }

    /**
     * @return The amount of decrease left.
     */
    public final int decreaseAmount(int decrease){
        if(decrease <= 0) {
            throw new IllegalArgumentException("Decrease is negative or 0.");
        }
        int actualDecrease = Math.min(decrease, amount);
        this.amount -= actualDecrease;
        return decrease - actualDecrease;
    }

    public Item copy(){
        return new Item(name, amount, stackSize);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Item)) {
            return false;
        }
        Item item = (Item) other;
        return name.equals(item.name) && stackSize == item.stackSize && amount == item.amount;
    }

    @Override
    public int hashCode(){
        int result = name.hashCode();
        result = result * 31 + stackSize;
        result = result * 31 + amount;
        return result;
    }

    @Override
    public String toString(){
        return String.format("Item[name=%s, amount=%d, stackSize=%d]", name, amount, stackSize);
    }
}
