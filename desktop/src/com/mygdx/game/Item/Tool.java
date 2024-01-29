package com.mygdx.game.Item;

import java.util.Objects;

import static com.mygdx.game.Constants.TOOL_STACK_SIZE;

public class Tool extends Item{

    private final int proficiency;

    public Tool(Builder builder){
        super(Item.itemBuilder().name(builder.name).spritePath(builder.spritePath).stackSize(TOOL_STACK_SIZE));
        this.proficiency = builder.proficiency;
    }

    public int getProficiency(){
        return proficiency;
    }

    public static Builder toolBuilder(){
        return new Builder();
    }

    public static class Builder{
        private String name;
        private String spritePath;
        private int proficiency;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder spritePath(String spritePath){
            this.spritePath = spritePath;
            return this;
        }

        public Builder proficiency(int proficiency){
            this.proficiency = proficiency;
            return this;
        }

        public Tool build(){
            Objects.requireNonNull(name, "The name of a tool must not be null");
            Objects.requireNonNull(spritePath, "The spritePath of a tool must not be null");
            if(proficiency <= 0){
                throw new IllegalArgumentException("The proficiency of a tool has to be strictly positive");
            }
            return new Tool(this);
        }
    }
}
