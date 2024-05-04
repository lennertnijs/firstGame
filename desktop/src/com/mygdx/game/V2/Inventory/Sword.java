package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.General.GameObject;

public class Sword extends Tool{

    public Sword(ItemTemplate template, int damage, int durability){
        super(template, damage, durability);
    }

    @Override
    public void use(GameObject object) {

    }
}
