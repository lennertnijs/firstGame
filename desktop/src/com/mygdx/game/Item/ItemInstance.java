package com.mygdx.game.Item;

import java.util.Objects;

public class ItemInstance {
    private final Item item;
    private int amount;

    public ItemInstance(Builder builder){
        this.item = builder.item;
        this.amount = builder.amount;
    }

    public Item getItem(){
        return item;
    }

    public int getAmount(){
        return amount;
    }

    public boolean canIncreaseAmountBy(int increase){
        if(increase < 0){
            throw new IllegalArgumentException("The amount of an item instance must not be increased by a negative value");
        }
        return (amount + increase) <= item.getStackSize();
    }

    public void increaseAmount(int increase){
        if(increase < 0){
            throw new IllegalArgumentException("The amount of an item instance must not be increased by a negative value");
        }
        boolean canIncrease = canIncreaseAmountBy(increase);
        if(canIncrease){
            this.amount += increase;
        }else{
            throw new IllegalArgumentException("Could not increase the item instance amount");
        }
    }

    public boolean canDecreaseAmountBy(int decrease){
        if(decrease < 0){
            throw new IllegalArgumentException("The amount of an item instance must not be decreased by a negative value");
        }
        return (amount - decrease) >= 0;
    }

    public void decreaseAmount(int decrease){
        if(decrease < 0){
            throw new IllegalArgumentException("The amount of an item instance must not be decreased by a negative value");
        }
        boolean canDecrease = canDecreaseAmountBy(decrease);
        if(canDecrease){
            this.amount -= decrease;
        }else{
            throw new IllegalArgumentException("Could not decrease the item instance amount");
        }
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Item item;
        private int amount;

        public Builder(){
        }

        public Builder item(Item item){
            this.item = item;
            return this;
        }

        public Builder amount(int amount){
            this.amount = amount;
            return this;
        }

        public ItemInstance build(){
            Objects.requireNonNull(item, "The item of an item instance cannot be null");
            if(amount <= 0 || amount > item.getStackSize()){
                throw new IllegalArgumentException("The amount of an item instance has to be positive and within stack size range");
            }
            return new ItemInstance(this);
        }
    }
}
