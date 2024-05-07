package com.mygdx.game.Inventory;

import java.util.Objects;

public class Item {

    private final String name;
    private final int stackSize;
    private int amount;

    public Item(String name, int stackSize, int amount){
        Objects.requireNonNull(name, "Name is null.");
        if(stackSize <= 0) {
            throw new IllegalArgumentException("Stack size is negative or 0.");
        }
        if(amount <= 0 || amount > stackSize) {
            throw new IllegalArgumentException("Amount is smaller or bigger than the stack size..");
        }
        this.name = name;
        this.stackSize = stackSize;
        this.amount = amount;
    }

    public String getName(){
        return name;
    }

    public int getStackSize(){
        return stackSize;
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
        int actualIncrease = Math.min(increase, stackSize - amount);
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

    public boolean amountIsZero(){
        return amount == 0;
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
        return String.format("Item[name=%s, stackSize=%d, amount=%d]", name, stackSize, amount);
    }

    public Item copy(){
        return new Item(name, stackSize, amount);
    }
}
