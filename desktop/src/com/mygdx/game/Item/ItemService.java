package com.mygdx.game.Item;

import com.badlogic.gdx.graphics.Texture;

public class ItemService {

    private final ItemTextureRepository itemTextureRepository;

    public ItemService(){
        itemTextureRepository = new ItemTextureRepository();
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
