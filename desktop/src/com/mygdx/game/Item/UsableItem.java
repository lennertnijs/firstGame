package com.mygdx.game.Item;

public class UsableItem extends Item{

    private final int randomNumber;

    public UsableItem(Builder<?> builder){
        super(builder);
        randomNumber = builder.randomNumber;
    }

    public static class Builder<T extends Builder<T>> extends Item.Builder<T>{

        private int randomNumber;

        public T randomNumber(int number){
            this.randomNumber = number;
            return self();
        }

        @Override
        public UsableItem build(){
            return new UsableItem(this);
        }
    }

}
