package com.mygdx.game.V2.Inventory;

import java.util.Objects;

public class Item {

    private int amount;
    private final ItemTemplate template;

    public Item(int amount, ItemTemplate template){
        if(amount <= 0)
            throw new IllegalArgumentException("Amount is negative or zero.");
        Objects.requireNonNull(template, "Item template is null.");
        this.amount = amount;
        this.template = template;
    }

    public int getAmount(){
        return amount;
    }

    public ItemTemplate getTemplate(){
        return template;
    }

    public boolean canIncreaseBy(int increase){
        if(increase <= 0)
            throw new IllegalArgumentException("Increase is negative or 0.");
        return amount + increase <= template.maxStackSize();
    }

    public boolean canDecreaseBy(int decrease){
        if(decrease <= 0)
            throw new IllegalArgumentException("Decrease is negative or 0.");
        return amount - decrease >= 0;
    }

    public int increaseAmount(int increase){
        if(increase <= 0)
            throw new IllegalArgumentException("Increase is negative or 0.");
        int actualIncrease = Math.min(increase, template.maxStackSize() - amount);
        this.amount += actualIncrease;
        return increase - actualIncrease;
    }

    public void decreaseAmount(int decrease){
        if(decrease <= 0)
            throw new IllegalArgumentException("Decrease is negative or 0.");
        this.amount -= decrease;
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
        return String.format("Item[amount=%d, template=%s]", amount, template);
    }

}
