package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.General.GameObject;

/**
 * Represents a sword.
 */
public class Sword extends Tool{

    /**
     * Creates a new {@link Shovel}.
     * @param template The item template. Cannot be null.
     * @param efficiency The efficiency. Cannot be negative.
     * @param durability The durability. Cannot be negative.
     */
    public Sword(ItemTemplate template, int efficiency, int durability){
        super(template, efficiency, durability);
    }

    @Override
    public void use(GameObject object) {

    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Sword))
            return false;
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
