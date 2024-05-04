package com.mygdx.game.V2.Inventory;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

/**
 * Represents an item in the game.
 */
public class Item {

    private final ItemTemplate template;
    private int amount;

    /**
     * Creates a new {@link Item}.
     * @param template The {@link ItemTemplate}. Cannot be null.
     * @param amount The amount of the item. Cannot be negative. Cannot be zero. Cannot be bigger than its stack size.
     */
    public Item(ItemTemplate template, int amount){
        Objects.requireNonNull(template, "Item template is null.");
        if(amount <= 0 || amount > template.stackSize())
            throw new IllegalArgumentException("Amount is invalid.");
        this.template = template;
        this.amount = amount;
    }

    public ItemTemplate getTemplate(){
        return template;
    }

    public String getName(){
        return template.name();
    }

    public String getDescription(){
        return template.description();
    }

    public Texture getTexture(){
        return template.texture();
    }

    public int getStackSize(){
        return template.stackSize();
    }

    public int getAmount(){
        return amount;
    }

    /**
     * Increases the amount of this item with the given increase. If the increase leads to an amount that is larger
     * than the stack size of the item, it will set it to the stack size & return the amount of increase left.
     * @param increase The increase. Cannot be negative or 0.
     *
     * @return The amount of increase left.
     * @throws IllegalArgumentException If the increase is negative or 0.
     */
    public int increaseAmount(int increase){
        if(increase <= 0)
            throw new IllegalArgumentException("Increase is negative or 0.");
        int actualIncrease = Math.min(increase, template.stackSize() - amount);
        this.amount += actualIncrease;
        return increase - actualIncrease;
    }

    /**
     * Decreases the amount of this item with the given decrease. If the decrease leads to an amount that is smaller
     * than 0, it will set it to 0 & return the amount of decrease left.
     * @param decrease The decrease. Cannot be negative or 0.
     *
     * @return The amount of decrease left.
     * @throws IllegalArgumentException If the decrease is negative or 0.
     */
    public int decreaseAmount(int decrease){
        if(decrease <= 0)
            throw new IllegalArgumentException("Decrease is negative or 0.");
        int actualDecrease = Math.min(decrease, amount);
        this.amount -= actualDecrease;
        return decrease - actualDecrease;
    }

    /**
     * @return True if the amount of this item is 0. False otherwise.
     */
    public boolean amountIsZero(){
        return amount == 0;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Item))
            return false;
        Item item = (Item) other;
        return amount == item.amount && template.equals(item.template);
    }

    @Override
    public int hashCode(){
        int result = amount;
        result = result * 31 + template.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Item[template=%s, amount=%d]", template, amount);
    }
}
