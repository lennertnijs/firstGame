package com.mygdx.game.V2.Inventory;

import com.mygdx.game.V2.General.GameObject;

import java.util.Objects;

public final class InventoryManager {

    private final Inventory inventory;
    private int activeIndex;
    private final ItemTemplateRepository repository;

    public InventoryManager(Inventory inventory, int activeIndex, ItemTemplateRepository repository){
        Objects.requireNonNull(inventory, "Inventory is null.");
        if(activeIndex < 0 || activeIndex >= inventory.getItems().length){
            throw new IndexOutOfBoundsException("Active index is out of bounds.");
        }
        Objects.requireNonNull(repository, "Item template repository is null.");
        this.inventory = inventory;
        this.activeIndex = activeIndex;
        this.repository = repository;
    }

    public boolean contains(String name, int amount){
        return inventory.contains(name, amount);
    }

    public int addItem(String name, int amount){
        Objects.requireNonNull(name, "Name is null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or 0.");
        }
        int stackSize = repository.getFromName(name).stackSize();
        Item item = new Item(name, stackSize, amount);
        return inventory.addItem(item);
    }

    public void removeItem(String name, int amount){
        Objects.requireNonNull(name, "Name is null.");
        if(amount <= 0){
            throw new IllegalArgumentException("Amount is negative or 0.");
        }
        inventory.remove(name, amount);
    }

    public void incrementActiveIndex(){
        activeIndex = (++activeIndex)%inventory.getItems().length;
    }

    public void decrementActiveIndex(){
        activeIndex = (--activeIndex)%inventory.getItems().length;
    }

    public void useActive(GameObject object){
        inventory.use(activeIndex, object);
    }

    public void storeTemplate(ItemTemplate template){
        Objects.requireNonNull(template, "Item template is null.");
        repository.add(template);
    }
}
