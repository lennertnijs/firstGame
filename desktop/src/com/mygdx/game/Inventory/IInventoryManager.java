package com.mygdx.game.Inventory;

import com.mygdx.game.General.GameObject;

public interface IInventoryManager {

    boolean contains(String name, int amount);
    int addItem(String name, int amount);
    void removeItem(String name, int amount);
    void incrementActiveIndex();
    void decrementActiveIndex();
    void useActive(GameObject o);
    void storeTemplate(ItemTemplate template);

}
