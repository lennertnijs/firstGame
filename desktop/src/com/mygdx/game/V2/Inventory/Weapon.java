package com.mygdx.game.V2.Inventory;

public class Weapon extends Item implements Usable{

    private final int damage;
    private int durability;

    public Weapon(int amount, ItemTemplate template, int damage, int durability){
        super(amount, template);
        this.damage = damage;
        this.durability = durability;
    }

    public int getDamage(){
        return damage;
    }

    public int getDurability(){
        return durability;
    }

    public boolean hasDurabilityLeft(){
        return durability > 0;
    }

    public void use(){
        if(durability <= 0)
            throw new IllegalStateException("Durability is 0. Cannot be used.");
        this.durability--;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Weapon))
            return false;
        if(!super.equals(other))
            return false;
        Weapon weapon = (Weapon) other;
        return damage == weapon.damage && durability == weapon.durability;
    }

    @Override
    public int hashCode(){
        int result = super.hashCode();
        result = result * 31 + damage;
        result = result * 31 + durability;
        return result;
    }

    @Override
    public String toString(){
        return String.format("Weapon[damage=%d, durability=%d]", damage, durability);
    }
}
