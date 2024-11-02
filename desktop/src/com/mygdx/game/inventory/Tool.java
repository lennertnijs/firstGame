package com.mygdx.game.inventory;

import com.mygdx.game.game_object.GameObject;

import java.util.Objects;

/**
 * A tool class encapsulating all the data related to a tool.
 * A tool contains:
 * - the efficiency
 * - the current durability
 * - the maximum durability
 * - the type of tool
 * - the duration of usage
 * - the delta (used to make sure items aren't used too quickly)
 */
public final class Tool extends Item {

    private final int efficiency;
    private int durability;
    private final int maxDurability;
    private final ToolType type;
    private final int duration;
    private double delta;

    private Tool(Builder builder){
        super(builder.name, 1);
        this.efficiency = builder.efficiency;
        this.maxDurability = builder.maxDurability;
        this.durability = builder.durability;
        this.type = builder.toolType;
        this.duration = builder.duration;
        this.delta = 0;
    }

    public int efficiency(){
        return efficiency;
    }

    public int currentDurability(){
        return durability;
    }

    public int maxDurability(){
        return maxDurability;
    }

    public ToolType type(){
        return type;
    }

    public int duration(){
        return duration;
    }

    public double getDelta(){
        return delta;
    }

    @Override
    public void use(GameObject object){
        if(delta > 0 || durability == 0){
            return;
        }
        this.delta = duration;
        switch(type){
            case PICKAXE -> object.accept(new PickaxeVisitor());
            case SWORD -> object.accept(new SwordVisitor());
            case SHOVEL -> object.accept(new ShovelVisitor());
            case AXE -> object.accept(new AxeVisitor());
        }
        durability--;
    }

    @Override
    public void update(double delta){
        this.delta = Math.max(0, this.delta - delta);
    }

    @Override
    public String toString(){
        return String.format("Tool[name=%s, efficiency=%d, durability=%d, maxDurability=%d, type=%s]",
                super.name(), efficiency, durability, maxDurability, type);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private String name = null;
        private int efficiency = -1;
        private int durability = -1;
        private int maxDurability = -1;
        private ToolType toolType = null;
        private int duration;

        private Builder(){
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder efficiency(int efficiency){
            this.efficiency = efficiency;
            return this;
        }

        public Builder durability(int durability){
            this.durability = durability;
            return this;
        }

        public Builder maxDurability(int maxDurability){
            this.maxDurability = maxDurability;
            return this;
        }

        public Builder toolType(ToolType toolType){
            this.toolType = toolType;
            return this;
        }

        public Builder duration(int duration){
            this.duration = duration;
            return this;
        }

        public Tool build(){
            if(efficiency < 0) {
                throw new IllegalArgumentException("Efficiency cannot be negative.");
            }
            if(maxDurability <= 0){
                throw new IllegalArgumentException("Max durability cannot be negative or zero.");
            }
            if(durability > maxDurability){
                throw new IllegalArgumentException("Durability is bigger than the max durability.");
            }
            if(durability < 0) {
                this.durability = maxDurability; // full durability
            }
            Objects.requireNonNull(toolType, "Tool type cannot be null.");
            if(duration <= 0){
                throw new IllegalArgumentException("Duration cannot be negative or 0.");
            }
            return new Tool(this);
        }
    }
}
