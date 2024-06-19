package com.mygdx.game.Inventory;

import java.util.Objects;

/**
 * Base item.
 */
public class Item {

    /**
     * Name of the item.
     */
    private final String name;
    /**
     * Amount of the item.
     */
    private int amount;

    /**
     * Creates a new MUTABLE {@link Item}.
     * @param name The name. Cannot be null.
     * @param amount The amount. Cannot be negative or 0.
     */
    public Item(String name, int amount){
        this.name = Objects.requireNonNull(name, "Item name is null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount <= 0");
        }
        this.amount = amount;
    }

    /**
     * @return The name.
     */
    public String name(){
        return name;
    }

    /**
     * @return The amount
     */
    public int getAmount(){
        return amount;
    }


    /**
     * Attempts to increase the amount of this item with the given increase.
     * If (current amount + increase) <= stack size, adds all of it.
     * If (current amount + increase) > stack size, adds up to stack size and returns the remainder.
     * @param increase The amount to increase. Cannot be negative or 0.
     * @param stackSize The stack size. Cannot be negative or 0.
     *
     * @return The remainder. (0 if all was added)
     * @throws IllegalArgumentException If the current amount exceeds the given stack size.
     */
    public int increaseAmount(int increase, int stackSize){
        if(increase <= 0) {
            throw new IllegalArgumentException("Increase is negative or 0.");
        }
        if(stackSize <= 0){
            throw new IllegalArgumentException("Stack size is negative or 0.");
        }
        if(amount > stackSize){
            throw new IllegalArgumentException("Current amount is larger than the stack size already.");
        }
        int actualIncrease = Math.min(increase, stackSize - amount);
        this.amount += actualIncrease;
        return increase - actualIncrease;
    }

    /**
     * Attempts to decrease the amount of this item with the given decrease.
     * If (current amount - decrease) >= 0, adds all of it.
     * If (current amount - decrease) < 0, removes until 0 and returns the remainder.
     * @param decrease The amount to decrease. Cannot be negative or 0.
     *
     * @return The remainder. (0 if all was removed)
     *
     */
    public int decreaseAmount(int decrease){
        if(decrease <= 0) {
            throw new IllegalArgumentException("Decrease is negative or 0.");
        }
        int actualDecrease = Math.min(decrease, amount);
        this.amount -= actualDecrease;
        return decrease - actualDecrease;
    }

    /**
     * @return A copy of this item.
     */
    public Item copy(){
        return new Item(name, amount);
    }

    /**
     * @return The string representation.
     */
    @Override
    public String toString(){
        return String.format("Item[name=%s, amount=%d]", name, amount);
    }
}
