package com.mygdx.game.Stone;

import com.mygdx.game.Entity.Position;
import com.mygdx.game.Interactive.Interactive;

import java.util.Objects;

public class Stone implements Interactive {

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

    protected void damage(float damage){
        if(damage < 0){
            throw new IllegalArgumentException("Cannot negatively damage rocc");
        }
        this.healthPoints -= damage;
    }


    protected boolean isBroken(){
        return healthPoints <= 0;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Stone)){
            return false;
        }
        Stone stone = (Stone) o;
        return position.equals(stone.position) && healthPoints == stone.healthPoints && hardness == stone.hardness;
    }

    @Override
    public int hashCode(){
        return Objects.hash(position, healthPoints, hardness);
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
