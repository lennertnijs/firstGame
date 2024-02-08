package com.mygdx.game.Player;

import com.mygdx.game.Direction;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.Item.Item;
import com.mygdx.game.NPC.Activity;

import java.util.Objects;

public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(){
        playerRepository = new PlayerRepository();
    }

    public Player getPlayer(){
        return playerRepository.getPlayer();
    }

    public void setItemIndex(int index){
        int inventorySize = playerRepository.getPlayer().getInventory().getSize();
        if(index < 0 || index >= inventorySize){
            throw new IllegalArgumentException("Cannot set the active item index to a value outside the inventory range");
        }
        playerRepository.getPlayer().setCurrentItemIndex(index);
    }

    public void setActivity(Activity activity){
        Objects.requireNonNull(activity, "Cannot set the player activity to null");
        playerRepository.getPlayer().setActivity(activity);
    }

    public void setDirection(Direction direction){
        Objects.requireNonNull(direction, "Cannot set the player direction to null");
        playerRepository.getPlayer().setDirection(direction);
    }

    public void setPosition(Position position){
        Objects.requireNonNull("Cannot set the player position to null");
        playerRepository.getPlayer().setPosition(position);
    }

    public void setPosition(int x, int y){
        if(x < 0 || y < 0){
            throw new IllegalArgumentException("Cannot accept negative coordinates");
        }
        Position position = Position.builder().x(x).y(y).build();
        playerRepository.getPlayer().setPosition(position);
    }

    public void setDoingAnimation(boolean bool){
        playerRepository.getPlayer().setDoingAnimation(bool);
    }

    public void addItemToInventory(Item item){
        Objects.requireNonNull(item, "Cannot add a null item to the inventory");
        boolean freeItemSlot = playerRepository.getPlayer().getInventory().hasEmptySlot();
        if(freeItemSlot){
            playerRepository.getPlayer().getInventory().addItem(item);
        }
    }

    public void removeItemFromInventory(int index){
        Inventory inventory = playerRepository.getPlayer().getInventory();
        if(index < 0 || index > inventory.getSize()){
            throw new IllegalArgumentException("Index out of inventory bounds");
        }
        inventory.removeItem(index);
    }
}
