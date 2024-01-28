package com.mygdx.game.Item;

import java.util.Objects;

public class ItemInstance {
    private final Item usableItem;
    private int amount;

    public ItemInstance(Builder builder){
        this.usableItem = builder.usableItem;
        this.amount = builder.amount;
    }

    public Item getItem(){
        return usableItem;
    }

    public int getAmount(){
        return amount;
    }

    public boolean canIncreaseAmountBy(int increase){
        if(increase < 0){
            throw new IllegalArgumentException("The amount of an item instance must not be increased by a negative value");
        }
        return (amount + increase) <= usableItem.getStackSize();
    }

    public boolean canDecreaseAmountBy(int decrease){
        if(decrease < 0){
            throw new IllegalArgumentException("The amount of an item instance must not be decreased by a negative value");
        }
        return (amount - decrease) >= 0;
    }

    public void increaseAmount(int increase){
        if(increase < 0){
            throw new IllegalArgumentException("The amount of an item instance must not be increased by a negative value");
        }
        boolean canIncrease = canIncreaseAmountBy(increase);
        if(canIncrease){
            this.amount += increase;
            return;
        }
        throw new IllegalArgumentException("Could not increase the item instance amount");
    }

    public void decreaseAmount(int decrease){
        if(decrease < 0){
            throw new IllegalArgumentException("The amount of an item instance must not be increased by a negative value");
        }
        boolean canIncrease = canDecreaseAmountBy(decrease);
        if(canIncrease){
            this.amount -= decrease;
            return;
        }
        throw new IllegalArgumentException("Could not decrease the item instance amount");
    }



    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Item usableItem;
        private int amount;

        public Builder(){

        }

        public Builder item(Item usableItem){
            this.usableItem = usableItem;
            return this;
        }

        public Builder amount(int amount){
            this.amount = amount;
            return this;
        }

        public ItemInstance build(){
            Objects.requireNonNull(usableItem, "The item of an item instance cannot be null");
            if(amount <= 0 || amount > usableItem.getStackSize()){
                throw new IllegalArgumentException("The amount of an item instance has to be positive");
            }
            return new ItemInstance(this);
        }
    }
}
