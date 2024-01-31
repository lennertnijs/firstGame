package com.mygdx.game.Item;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

import static com.mygdx.game.Constants.TOOL_STACK_SIZE;

public class Tool extends Item{

    private final int efficiency;
    private final ToolType toolType;

    public Tool(Builder builder){
        super(Item.itemBuilder().name(builder.name).texture(builder.texture).stackSize(TOOL_STACK_SIZE));
        this.efficiency = builder.efficiency;
        this.toolType = builder.toolType;
    }

    public int getEfficiency(){
        return efficiency;
    }

    public ToolType getToolType(){
        return toolType;
    }

    public static Builder toolBuilder(){
        return new Builder();
    }

    public static class Builder{
        private String name;

        private Texture texture;
        private int efficiency;
        private ToolType toolType;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder texture(Texture texture){
            this.texture = texture;
            return this;
        }

        public Builder efficiency(int efficiency){
            this.efficiency = efficiency;
            return this;
        }

        public Builder toolType(ToolType toolType){
            this.toolType = toolType;
            return this;
        }

        public Tool build(){
            Objects.requireNonNull(name, "The name of a tool must not be null");
            if(efficiency <= 0){
                throw new IllegalArgumentException("The proficiency of a tool has to be strictly positive");
            }
            return new Tool(this);
        }
    }
}
