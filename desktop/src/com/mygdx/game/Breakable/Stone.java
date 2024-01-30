package com.mygdx.game.Breakable;

import com.mygdx.game.Entity.Position;

import java.util.Objects;

public class Stone {

    private final Position position;
    private float healthPoints;
    private final int hardness;

    public Stone(Builder builder){
        position = builder.position;
        healthPoints = builder.healthPoints;
        hardness = builder.hardness;
    }

    public Position getPosition(){
        return position;
    }

    public float getHealthPoints(){
        return healthPoints;
    }

    public int getHardness(){
        return hardness;
    }

    public boolean isBroken(){
        return healthPoints <= 0;
    }

    public void damage(float damage){
        if(damage < 0){
            throw new IllegalArgumentException("Cannot negatively damage rocc");
        }
        this.healthPoints -= damage;
    }




    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private Position position;
        private float healthPoints;
        private int hardness;

        private Builder(){

        }

        public Builder position(Position position){
            this.position = position;
            return this;
        }

        public Builder healthPoints(float healthPoints){
            this.healthPoints = healthPoints;
            return this;
        }

        public Builder hardness(int hardness){
            this.hardness = hardness;
            return this;
        }

        public Stone build(){
            Objects.requireNonNull(position, "Could not build the stone");
            if(healthPoints <= 0){
                throw new IllegalArgumentException("Could not build the stone");
            }
            if(hardness <= 0){
                throw new IllegalArgumentException("Cannot build the stone");
            }
            return new Stone(this);
        }
    }
}
