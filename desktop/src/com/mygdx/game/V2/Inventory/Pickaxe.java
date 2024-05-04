package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.General.GameObject;

/**
 * Represents a pickaxe.
 */
public final class Pickaxe extends Tool{

    /**
     * Creates a new {@link Pickaxe}.
     * @param template The item template. Cannot be null.
     * @param efficiency The efficiency. Cannot be negative.
     * @param durability The durability. Cannot be negative.
     */
    public Pickaxe(ItemTemplate template, int efficiency, int durability) {
        super(template, efficiency, durability);
    }

    @Override
    public void use(GameObject object) {
        // if game object is a stone -> go brr
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Pickaxe))
            return false;
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
