package com.mygdx.game.Item;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

public class Weapon extends Item{

    private final int damage;
    public Weapon(Builder builder){
        super(Item.itemBuilder().name(builder.name).texture(builder.texture).stackSize(builder.stackSize));
        damage = builder.damage;
    }

    public int getDamage(){
        return damage;
    }

    public static Builder weaponBuilder(){
        return new Builder();
    }

    public static class Builder{

        // Item fields
        private String name;
        private final int stackSize = 1;
        private Texture texture;

        // Weapon fields
        private int damage;


        public Builder(){
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder texture(Texture texture){
            this.texture = texture;
            return this;
        }

        public Builder damage(int damage){
            this.damage = damage;
            return this;
        }

        public Weapon build(){
            Objects.requireNonNull(name, "The name of a weapon must not be null");
            if(damage <= 0){
                throw new IllegalArgumentException("The damage of a weapon has to be strictly positive");
            }
            return new Weapon(this);
        }
    }
}
