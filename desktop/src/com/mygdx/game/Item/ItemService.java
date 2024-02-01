package com.mygdx.game.Item;

import com.badlogic.gdx.graphics.Texture;

public class ItemService {

    private final ItemTextureRepository itemTextureRepository;

    protected ItemService(){
        itemTextureRepository = new ItemTextureRepository();
    }

    protected Texture getItemTexture(int itemId){
        return itemTextureRepository.getItemTexture(itemId);
    }

    protected boolean canUseTool(Tool tool){
        return tool.getDurability() > 0;
    }

    protected void useTool(Tool tool){
        int durability = tool.getDurability();
        if(canUseTool(tool)){
            tool.setDurability(--durability);
        }
    }

    protected boolean canUseWeapon(Weapon weapon){
        return weapon.getDurability() > 0;
    }

    protected void useWeapon(Weapon weapon){
        int durability = weapon.getDurability();
        if(canUseWeapon(weapon)){
            weapon.setDurability(--durability);
        }
    }

    protected boolean isEmptyStack(Item item){
        return item.getAmount() == 0;
    }

    protected boolean canIncreaseByAmount(Item item, int amount){
        return item.getAmount() + amount <= item.getStackSize();
    }

    protected boolean canDecreaseByAmount(Item item, int amount){
        return item.getAmount() - amount >= 0;
    }

    protected void increaseByAmount(Item item, int amount){
        if(canIncreaseByAmount(item, amount)){
            item.setAmount(item.getAmount() + amount);
        }
    }

    protected void decreaseByAmount(Item item, int amount){
        if(canDecreaseByAmount(item, amount)){
            item.setAmount(item.getAmount() - amount);
        }
    }
}
