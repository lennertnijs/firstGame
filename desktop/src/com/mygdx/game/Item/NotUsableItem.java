package com.mygdx.game.Item;

public class NotUsableItem extends Item{

    protected NotUsableItem(Builder<?> builder){
        super(builder);
    }

    public static class Builder<T extends Builder<T>> extends Item.Builder<T>{

        public NotUsableItem build(){
            return new NotUsableItem(this);
        }
    }
}
