package com.mygdx.game.Item;

import java.util.Objects;

public class Weapon extends Item{

    private final int damage;
    public Weapon(Builder builder){
        super(Item.itemBuilder().name(builder.name).spritePath(builder.spritePath).stackSize(builder.stackSize));
        damage = builder.damage;
    }

    public int getDamage(){
        return damage;
    }

    public static Builder weaponBuilder(){
        return new Builder();
    }

    public static class Builder{

        private String name;
        private String spritePath;
        private final int stackSize = 1;
        private int damage;


        public Builder(){
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder spritePath(String spritePath){
            this.spritePath = spritePath;
            return this;
        }

        public Builder damage(int damage){
            this.damage = damage;
            return this;
        }

        public Weapon build(){
            Objects.requireNonNull(name, "The name of a weapon must not be null");
            Objects.requireNonNull(spritePath, "The spritePath of a weapon must not be null");
            if(damage <= 0){
                throw new IllegalArgumentException("The damage of a weapon has to be strictly positive");
            }
            return new Weapon(this);
        }
    }
}
