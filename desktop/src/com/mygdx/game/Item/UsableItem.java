package com.mygdx.game.Item;

public class UsableItem extends Item{

    public UsableItem(Builder<?> builder){
        super(builder);
    }

    public static class Builder<T extends Builder<T>> extends Item.Builder<T>{

        @Override
        public UsableItem build(){
            return new UsableItem(this);
        }
    }

}
