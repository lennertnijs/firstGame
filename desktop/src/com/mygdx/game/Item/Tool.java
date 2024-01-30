package com.mygdx.game.Item;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

import static com.mygdx.game.Constants.TOOL_STACK_SIZE;

public class Tool extends Item{

    private final int proficiency;
    private final ToolType toolType;

    public Tool(Builder builder){
        super(Item.itemBuilder().name(builder.name).texture(builder.texture).stackSize(TOOL_STACK_SIZE));
        this.proficiency = builder.proficiency;
        this.toolType = builder.toolType;
    }

    public int getProficiency(){
        return proficiency;
    }

    public static Builder toolBuilder(){
        return new Builder();
    }

    public static class Builder{
        private String name;

        private Texture texture;
        private int proficiency;
        private ToolType toolType;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder texture(Texture texture){
            this.texture = texture;
            return this;
        }

        public Builder proficiency(int proficiency){
            this.proficiency = proficiency;
            return this;
        }

        public Builder toolType(ToolType toolType){
            this.toolType = toolType;
            return this;
        }

        public Tool build(){
            Objects.requireNonNull(name, "The name of a tool must not be null");
            if(proficiency <= 0){
                throw new IllegalArgumentException("The proficiency of a tool has to be strictly positive");
            }
            return new Tool(this);
        }
    }
}
