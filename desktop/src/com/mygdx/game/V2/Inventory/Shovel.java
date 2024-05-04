package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.General.GameObject;

public final class Shovel extends Tool{

    public Shovel(ItemTemplate template, int efficiency, int durability) {
        super(1, template, efficiency, durability);
    }

    @Override
    public void use(GameObject object) {

    }
}
