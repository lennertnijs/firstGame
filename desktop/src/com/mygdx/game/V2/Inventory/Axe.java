package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.General.GameObject;

/**
 * Represents an axe.
 */
public final class Axe extends Tool{

    /**
     * Creates a new {@link Axe}.
     * @param template The item template. Cannot be null.
     * @param efficiency The efficiency. Cannot be negative.
     * @param durability The durability. Cannot be negative.
     */
    public Axe(ItemTemplate template, int efficiency, int durability) {
        super(template, efficiency, durability);
    }

    /**
     * Use this axe on the given game object. To be added.
     * @param object The game object that it should be used on.
     */
    @Override
    public void use(GameObject object) {

    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Axe))
            return false;
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
