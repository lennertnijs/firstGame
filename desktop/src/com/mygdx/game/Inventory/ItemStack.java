package com.mygdx.game.Inventory;

import java.util.Objects;

public final class ItemStack {

    /**
     * The name of the item.
     */
    private final Item item;
    private int amount;
    /**
     * The stack size (aka maximum amount).
     */
    private final int stackSize;

    /**
     * Creates a new MUTABLE {@link ItemStack}.
     * @param item The name of the item.
     * @param amount The amount of the item. Amount cannot be negative or 0.
     * @param stackSize The size of a stack. Stack size cannot be negative or 0.
     */
    public ItemStack(Item item, int amount, int stackSize){
        Objects.requireNonNull(item, "Item is null.");
        if(stackSize <= 0) {
            throw new IllegalArgumentException("Stack size is negative or 0.");
        }
        if(amount <= 0 || amount > stackSize) {
            throw new IllegalArgumentException("Amount is negative, zero or bigger than the max stack size..");
        }
        this.item = item;
        this.amount = amount;
        this.stackSize = stackSize;
    }

    /**
     * @return The name
     */
    public Item item(){
        return item;
    }

    /**
     * @return The amount
     */
    public int getAmount(){
        return amount;
    }

    /**
     * @return The stack size
     */
    public int stackSize(){
        return stackSize;
    }

    /**
     * @return True if this stack is full. False otherwise.
     */
    public boolean isFull(){
        return amount == stackSize;
    }

    /**
     * Attempts to increase the amount of this item with the given increase.
     * If (current amount + increase) <= stack size, adds all of it.
     * If (current amount + increase) > stack size, adds up to stack size and returns the remainder.
     *
     * @return The remainder. (0 if all was added)
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
     * Attempts to decrease the amount of this item with the given decrease.
     * If (current amount - decrease) >= 0, adds all of it.
     * If (current amount - decrease) < 0, removes until 0 and returns the remainder.
     *
     * @return The remainder. (0 if all was removed)
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
     * @return A deep copy of this item stack.
     */
    public ItemStack copy(){
        return new ItemStack(item, amount, stackSize);
    }

    /**
     * @return The string representation.
     */
    @Override
    public String toString(){
        return String.format("ItemStack[%s, amount=%d, stackSize=%d]", item, amount, stackSize);
    }
}
