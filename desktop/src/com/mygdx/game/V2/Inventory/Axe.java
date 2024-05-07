package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.General.GameObject;

public final class Axe extends Tool{

    public Axe(String name, int efficiency, int durability) {
        super(name, efficiency, durability);
    }

    @Override
    public void use(GameObject object) {

    }

    @Override
    public Axe copy(){
        return new Axe(getName(), getEfficiency(), getDurability());
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Axe)) {
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
        return String.format("Axe[amount=%d, efficiency=%d, durability=%d]",
                getAmount(), getEfficiency(), getDurability());
    }
}
