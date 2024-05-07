package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.General.GameObject;

public final class Shovel extends Tool{

    public Shovel(String name, int efficiency, int durability) {
        super(name, efficiency, durability);
    }

    @Override
    public void use(GameObject object) {

    }

    @Override
    public Shovel copy(){
        return new Shovel(getName(), getEfficiency(), getDurability());
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Shovel)) {
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
        return String.format("Shovel[amount=%d, efficiency=%d, durability=%d]",
                getAmount(), getEfficiency(), getDurability());
    }
}
