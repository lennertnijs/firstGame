package com.mygdx.game.Inventory;

import java.util.Objects;

/**
 * Base item. Has no functionality, besides a name.
 */
public class Item {

    /**
     * Name of the item.
     */
    private final String name;

    /**
     * Creates a new IMMUTABLE {@link Item}.
     * @param name The name. Cannot be null.
     */
    public Item(String name){
        this.name = Objects.requireNonNull(name, "Item name is null.");
    }

    /**
     * @return The name.
     */
    public String name(){
        return name;
    }

    /**
     * Compares two item objects and returns true if they're equal. Returns false otherwise.
     *
     * @return True if equal, false otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Item))
            return false;
        Item item = (Item) other;
        return name.equals(item.name);
    }

    /**
     * @return The hash code.
     */
    @Override
    public int hashCode(){
        return name.hashCode();
    }

    /**
     * @return The string representation.
     */
    @Override
    public String toString(){
        return String.format("Item[name=%s]", name);
    }
}
