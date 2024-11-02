package com.mygdx.game.inventory;

import com.mygdx.game.game_object.GameObject;

import java.util.Objects;

/**
 * A mutable generic base item. Can be extended.
 * A generic item object contains:
 * - a name (to identify items)
 * - an amount
 * - a use() and update() method that do nothing. They serve for child classes to implement more complex behaviours.
 */
public class Item {

    private final String name;
    private int amount;

    public Item(String name, int amount){
        this.name = Objects.requireNonNull(name);
        if(amount <= 0){
            throw new IllegalArgumentException("Amount cannot be negative or 0.");
        }
        this.amount = amount;
    }

    public final String name(){
        return name;
    }

    public final int getAmount(){
        return amount;
    }

    /**
     * Attempts to increase the amount of this item with the given increase, up to the stack size limit.
     * Returns the remainder, or 0 if the entire amount was added.
     */
    public final int increaseAmount(int increase, int stackSize){
        if(increase <= 0) {
            throw new IllegalArgumentException("Increase cannot be negative or 0.");
        }
        if(stackSize <= 0){
            throw new IllegalArgumentException("Stack size cannot negative or 0.");
        }
        if(amount > stackSize){
            throw new IllegalStateException("Current amount is larger than the stack size.");
        }
        int actualIncrease = Math.min(increase, stackSize - amount);
        this.amount += actualIncrease;
        return increase - actualIncrease;
    }

    /**
     * Attempts to decrease the amount of this item with the given decrease, down to 0.
     * Returns the remainder, or 0 if the entire amount was removed.
     */
    public final int decreaseAmount(int decrease){
        if(decrease <= 0) {
            throw new IllegalArgumentException("Decrease is negative or 0.");
        }
        int actualDecrease = Math.min(decrease, amount);
        this.amount -= actualDecrease;
        return decrease - actualDecrease;
    }

    public void use(GameObject object){
        // base class does nothing
    }

    public int usageDuration(){
        return 0;
    }

    public void update(double delta){
        // base class does nothing
    }

    @Override
    public String toString(){
        return String.format("Item[name=%s, amount=%d]", name, amount);
    }
}
