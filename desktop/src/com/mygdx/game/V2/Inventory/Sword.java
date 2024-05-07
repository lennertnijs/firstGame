package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.General.GameObject;

public class Sword extends Tool{

    public Sword(String name, int efficiency, int durability){
        super(name, efficiency, durability);
    }

    @Override
    public void use(GameObject object) {

    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Sword)) {
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
        return String.format("Sword[amount=%d, efficiency=%d, durability=%d]",
                getAmount(), getEfficiency(), getDurability());
    }
}
