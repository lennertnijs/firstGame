package com.mygdx.game.UpdatedItem;

public enum ItemType {

    WOOD(64),
    STONE(64),
    SAND(64),
    PICKAXE(1),
    AXE(1),
    SWORD(1),
    BOW(1);

    public final int stackSize;

    ItemType(int stackSize) {
        this.stackSize = stackSize;
    }
}
