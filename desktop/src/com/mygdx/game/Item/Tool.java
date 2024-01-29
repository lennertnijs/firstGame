package com.mygdx.game.Item;

public class Tool extends Item{

    private final int proficiency;

    public Tool(Builder<?> builder){
        super(builder);
        this.proficiency = builder.proficiency;
    }

    public int getProficiency(){
        return proficiency;
    }

    public static class Builder<T extends Builder<T>> extends Item.Builder<T>{

        private int proficiency;


        public T proficiency(int proficiency){
            this.proficiency = proficiency;
            return self();
        }

        @Override
        public Tool build(){
            if(proficiency <= 0){
                throw new IllegalArgumentException("The proficiency of a tool has to be strictly positive");
            }
            return new Tool(this);
        }
    }
}
