package com.mygdx.game.Item;

import java.util.Objects;

import static com.mygdx.game.Constants.WEAPON_STACK_SIZE;

public class Weapon extends Item{

    private final int damage;
    private int durability;
    private final WeaponType weaponType;


    public Weapon(Builder builder){
        super(Item.itemBuilder().itemId(builder.itemId).name(builder.name).stackSize(WEAPON_STACK_SIZE));
        this.damage = builder.damage;
        this.durability = builder.durability;
        this.weaponType = builder.weaponType;
    }

    public int getDamage(){
        return damage;
    }

    public final int getDurability(){
        return durability;
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
        return  getItemId() == weapon.getItemId() &&
                getName().equals(weapon.getName()) &&
                getStackSize() == weapon.getStackSize() &&
                damage == weapon.damage &&
                durability == weapon.durability &&
                weaponType.equals(weapon.weaponType);
    }

    @Override
    public int hashCode(){
        return Objects.hash(getItemId(), getName(), getStackSize(), damage, durability, weaponType);
    }



    public static Builder weaponBuilder(){
        return new Builder();
    }

    public static class Builder{

        // Item fields
        private int itemId;
        private String name;

        // Weapon fields
        private int damage;
        private int durability;
        private WeaponType weaponType;


        private Builder(){
        }

        public Builder itemId(int itemId){
            this.itemId = itemId;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder damage(int damage){
            this.damage = damage;
            return this;
        }

        public Builder durability(int durability){
            this.damage = durability;
            return this;
        }

        public Builder weaponType(WeaponType weaponType){
            this.weaponType = weaponType;
            return this;
        }

        public Weapon build(){
            if(itemId < 0){
                throw new IllegalArgumentException("The id of a weapon must not be negative");
            }
            Objects.requireNonNull(name, "The name of a weapon must not be null");
            if(damage <= 0){
                throw new IllegalArgumentException("The damage of a weapon has to be strictly positive");
            }
            if(durability < 0){
                throw new IllegalArgumentException("The durability of a weapon must not be negative");
            }
            Objects.requireNonNull(weaponType, "The weapon type must not be null");
            return new Weapon(this);
        }
    }
}
