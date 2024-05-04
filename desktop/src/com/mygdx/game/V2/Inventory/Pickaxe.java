package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.General.GameObject;

public final class Pickaxe extends Tool{


    public Pickaxe(ItemTemplate template, int efficiency, int durability) {
        super(1, template, efficiency, durability);
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
        return String.format("Pickaxe[%s]", super.toString());
    }
}
