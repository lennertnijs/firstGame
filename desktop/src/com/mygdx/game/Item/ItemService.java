package com.mygdx.game.Item;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

public class ItemService {

    private final ItemTextureRepository itemTextureRepository;

    public ItemService(){
        itemTextureRepository = new ItemTextureRepository();
    }

    public void setAmount(Item item, int amount){
        Objects.requireNonNull(item, "Cannot change the amount of a null item");
        if(amount < 0 || amount > item.getStackSize()){
            throw new IllegalArgumentException("Cannot set the item amount to an amount out of its bounds");
        }
        item.setAmount(amount);
    }

    public void setToolDurability(Tool tool, int durability){
        Objects.requireNonNull(tool, "Cannot change the durability of a null tool");
        if(durability < 0 || durability > tool.getDurability()){
            throw new IllegalArgumentException("Tool durability values must be positive and smaller than it's previous durability");
        }
        tool.setDurability(durability);
    }

    public void setWeaponDurability(Weapon weapon, int durability){
        Objects.requireNonNull(weapon, "Cannot change the durability of a null weapon");
        if(durability < 0 || durability > weapon.getDurability()){
            throw new IllegalArgumentException("Weapon durability values must be positive and smaller than it's previous durability");
        }
        weapon.setDurability(durability);
    }

    public Texture getItemTexture(int itemId){
        return itemTextureRepository.getItemTexture(itemId);
    }

    public boolean canUseTool(Tool tool){
        return tool.getDurability() > 0;
    }

    public void useTool(Tool tool){
        int durability = tool.getDurability();
        if(canUseTool(tool)){
            tool.setDurability(--durability);
        }
    }

    public boolean canUseWeapon(Weapon weapon){
        return weapon.getDurability() > 0;
    }

    public void useWeapon(Weapon weapon){
        int durability = weapon.getDurability();
        if(canUseWeapon(weapon)){
            weapon.setDurability(--durability);
        }
    }

    public boolean isEmptyStack(Item item){
        return item.getAmount() == 0;
    }

    public boolean canIncreaseByAmount(Item item, int amount){
        return item.getAmount() + amount <= item.getStackSize();
    }

    public boolean canDecreaseByAmount(Item item, int amount){
        return item.getAmount() - amount >= 0;
    }

    public void increaseByAmount(Item item, int amount){
        if(canIncreaseByAmount(item, amount)){
            item.setAmount(item.getAmount() + amount);
        }
    }

    public void decreaseByAmount(Item item, int amount){
        if(canDecreaseByAmount(item, amount)){
            item.setAmount(item.getAmount() - amount);
        }
    }
}
