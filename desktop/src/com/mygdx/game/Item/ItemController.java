package com.mygdx.game.Item;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    public Texture getTexture(int id){
        if(id < 0){
            throw new IllegalArgumentException("Cannot get item textures for negative ids");
        }
        return itemService.getItemTexture(id);
    }

    public boolean canUseTool(Tool tool){
        Objects.requireNonNull(tool, "Cannot use a null tool");
        return itemService.canUseTool(tool);
    }

    public void useTool(Tool tool){
        Objects.requireNonNull(tool, "Cannot use a null tool");
        itemService.useTool(tool);
    }

    public boolean canUseWeapon(Weapon weapon){
        Objects.requireNonNull(weapon, "Cannot use a null weapon");
        return itemService.canUseWeapon(weapon);
    }

    public void useWeapon(Weapon weapon){
        Objects.requireNonNull(weapon, "Cannot use a null weapon");
        itemService.useWeapon(weapon);
    }

    public boolean canIncrease(Item item, int amount){
        Objects.requireNonNull(item, "Cannot increase a null item");
        if(amount < 0){
            throw new IllegalArgumentException("Cannot increase an item by a negative amount");
        }
        return itemService.canIncreaseByAmount(item, amount);
    }

    public void increase(Item item, int amount){
        Objects.requireNonNull(item, "Cannot increase a null item");
        if(amount < 0){
            throw new IllegalArgumentException("Cannot increase an item by a negative amount");
        }
        itemService.increaseByAmount(item, amount);
    }

    public boolean canDecrease(Item item, int amount){
        Objects.requireNonNull(item, "Cannot decrease a null item");
        if(amount < 0){
            throw new IllegalArgumentException("Cannot decrease an item by a negative amount");
        }
        return itemService.canDecreaseByAmount(item, amount);
    }

    public void decrease(Item item, int amount){
        Objects.requireNonNull(item, "Cannot decrease a null item");
        if(amount < 0){
            throw new IllegalArgumentException("Cannot decrease an item by a negative amount");
        }
        itemService.decreaseByAmount(item, amount);
    }

    public boolean isEmpty(Item item){
        Objects.requireNonNull(item, "Cannot check if null is empty");
        return itemService.isEmptyStack(item);
    }
}

