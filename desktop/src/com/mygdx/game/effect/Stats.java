package com.mygdx.game.effect;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Stats {

    private int health;
    private int offense;
    private int defense;
    private int speed;
    private List<Effect> effects;

    private Stats(Builder builder){
        this.health = builder.health;
        this.offense = builder.offense;
        this.defense = builder.defense;
        this.speed = builder.speed;
        this.effects = new ArrayList<>();
    }

    public int getHealth(){
        return applyEffects(health, Stat.HEALTH);
    }

    public void setHealth(int health){
        if(health < 0){
            throw new IllegalArgumentException("Health cannot be negative.");
        }
        this.health = health;

    }

    public int getOffense(){
        return applyEffects(offense, Stat.OFFENSE);
    }

    public void setOffense(int offense){
        if(offense < 0){
            throw new IllegalArgumentException("Offense cannot be negative.");
        }
        this.offense = offense;
    }

    public int getDefense(){
        return applyEffects(defense, Stat.DEFENSE);
    }

    public void setDefense(int defense){
        if(defense < 0){
            throw new IllegalArgumentException("Defense cannot be negative.");
        }
        this.defense = defense;
    }

    public int getSpeed(){
        return applyEffects(speed, Stat.SPEED);
    }

    public void setSpeed(int speed){
        if(speed < 0){
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        this.speed = speed;
    }

    public void addEffect(Effect effect){
        this.effects.add(Objects.requireNonNull(effect));
    }

    public void clearEffects(){
        this.effects = new ArrayList<>();
    }

    public int applyEffects(int baseValue, Stat stat){
        for(Effect effect : effects){
            if(effect.stat() != stat) continue;
            switch(effect.type()){
                case FLAT -> baseValue += effect.amount();
                case PERCENT -> baseValue *= effect.amount();
            }
        }
        return baseValue;
    }

    @Override
    public String toString(){
        return String.format("Stats[health=%d, offense=%d, defense=%d, speed=%d]", health, offense, defense, speed);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private int health;
        private int offense;
        private int defense;
        private int speed;

        private Builder(){
        }

        public Builder health(int health){
            this.health = health;
            return this;
        }

        public Builder offense(int offense){
            this.offense = offense;
            return this;
        }

        public Builder defense(int defense){
            this.defense = defense;
            return this;
        }

        public Builder speed(int speed){
            this.speed = speed;
            return this;
        }

        public Stats build(){
            if(health < 0){
                throw new IllegalArgumentException("Health cannot be negative.");
            }
            if(offense < 0){
                throw new IllegalArgumentException("Offense cannot be negative.");
            }
            if(defense < 0){
                throw new IllegalArgumentException("Defense cannot be negative.");
            }
            if(speed < 0){
                throw new IllegalArgumentException("Speed cannot be negative.");
            }
            return new Stats(this);
        }

    }
}
