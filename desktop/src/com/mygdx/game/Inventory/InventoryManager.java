package com.mygdx.game.Inventory;

import com.mygdx.game.General.GameObject;

import java.util.Objects;

public final class InventoryManager implements IInventoryManager{

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
        return inventory.addItem(createItem(name, amount));
    }

    private Item createItem(String name, int amount){
        ItemTemplate template = repository.getFromName(name);
        if(template instanceof ToolTemplate){
            ToolTemplate template1 = (ToolTemplate) repository.getFromName(name);
            return new Tool(name, template1.efficiency(), template1.maxDurability(), ToolType.PICKAXE);
        }
        return new Item(name, template.stackSize(), amount);
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
