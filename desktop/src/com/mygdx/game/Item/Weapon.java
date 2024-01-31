package com.mygdx.game.Item;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

public class Weapon extends Item{

    private final int damage;
    private final WeaponType weaponType;

    public Weapon(Builder builder){
        super(Item.itemBuilder().name(builder.name).texture(builder.texture).stackSize(builder.stackSize));
        this.damage = builder.damage;
        this.weaponType = builder.weaponType;
    }

    public int getDamage(){
        return damage;
    }

    public WeaponType getWeaponType(){
        return weaponType;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Weapon)){
            return false;
        }
        Weapon weapon = (Weapon) o;
        return getName().equals(weapon.getName()) &&
                getStackSize() == weapon.getStackSize() &&
                damage == weapon.damage &&
                weaponType.equals(weapon.weaponType);
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getStackSize(), damage, weaponType);
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
        private WeaponType weaponType;


        private Builder(){
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

        public Builder weaponType(WeaponType weaponType){
            this.weaponType = weaponType;
            return this;
        }

        public Weapon build(){
            Objects.requireNonNull(name, "The name of a weapon must not be null");
            if(damage <= 0){
                throw new IllegalArgumentException("The damage of a weapon has to be strictly positive");
            }
            Objects.requireNonNull(weaponType, "The weapon type must not be null");
            return new Weapon(this);
        }
    }
}
