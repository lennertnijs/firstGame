package com.mygdx.game.Item;

public class Tool extends UsableItem{

    private final int durability;
    private final int proficiency;

    public Tool(Builder<?> builder){
        super(builder);
        this.durability = builder.durability;
        this.proficiency = builder.proficiency;
    }

    public int getDurability(){
        return durability;
    }

    public int getProficiency(){
        return proficiency;
    }

    public static class Builder<T extends Builder<T>> extends UsableItem.Builder<T>{
        private int durability;
        private int proficiency;

        public T durability(int durability){
            this.durability = durability;
            return self();
        }

        public T proficiency(int proficiency){
            this.proficiency = proficiency;
            return self();
        }

        @Override
        public Tool build(){
            if(durability <= 0){
                throw new IllegalArgumentException("The durability of a tool has to be strictly positive");
            }
            if(proficiency <= 0){
                throw new IllegalArgumentException("The proficiency of a tool has to be strictly positive");
            }
            return new Tool(this);
        }
    }
}
