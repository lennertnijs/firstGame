package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.General.GameObject;

public final class Pickaxe extends Tool{

    public Pickaxe(String name, int efficiency, int durability) {
        super(name, efficiency, durability);
    }

    @Override
    public void use(GameObject object) {
        // if game object is a stone -> go brr
    }

    @Override
    public Pickaxe copy(){
        return new Pickaxe(getName(), getEfficiency(), getDurability());
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Pickaxe)) {
            return false;
        }
        return super.equals(other);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public String toString(){
        return String.format("Pickaxe[amount=%d, efficiency=%d, durability=%d]",
                getAmount(), getEfficiency(), getDurability());
    }
}
