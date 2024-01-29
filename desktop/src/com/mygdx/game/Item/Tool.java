package com.mygdx.game.Item;

public class Tool extends UsableItem{

    private int toolId;
    public Tool(Builder<?> builder){
        super(builder);
        this.toolId = builder.toolId;
    }

    public static class Builder<T extends Builder<T>> extends UsableItem.Builder<T>{
        private int toolId;

        public T toolId(int id){
            this.toolId = toolId;
            return self();
        }

        @Override
        public Tool build(){
            return new Tool(this);
        }
    }
}
