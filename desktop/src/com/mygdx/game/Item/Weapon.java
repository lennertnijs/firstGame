package com.mygdx.game.Item;

public class Weapon extends Item{

    private final int damage;
    public Weapon(Builder<?> builder){
        super(builder);
        damage = builder.damage;
    }

    public int getDamage(){
        return damage;
    }

    public static class Builder<T extends Builder<T>> extends Item.Builder<T>{

        private int damage;
        public Builder(){
        }

        public T damage(int damage){
            this.damage = damage;
            return self();
        }

        @Override
        public Weapon build(){
            if(damage <= 0){
                throw new IllegalArgumentException("The damage of a weapon has to be strictly positive");
            }
            return new Weapon(this);
        }
    }

}
