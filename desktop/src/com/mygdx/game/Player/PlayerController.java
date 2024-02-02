package com.mygdx.game.Player;

import com.mygdx.game.Direction;
import com.mygdx.game.Entity.Position;
import com.mygdx.game.NPC.Activity;

import java.util.Objects;

public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    public Player getPlayer(){
        return playerService.getPlayer();
    }

    public void setPosition(Position position){
        Objects.requireNonNull(position, "Cannot set the player position to null");
        playerService.setPosition(position);
    }

    public void setActiveItemIndex(int index){
        int inventorySize = playerService.getPlayer().getInventory().getSize();
        if(index < 0 || index > inventorySize){
            throw new IllegalArgumentException("Cannot set the active item index outside inventory bounds");
        }
        playerService.setItemIndex(index);
    }

    public void setInAnimation(boolean bool){
        playerService.setDoingAnimation(bool);
    }

    public void setActivity(Activity activity){
        Objects.requireNonNull(activity, "Cannot set the player activity to null");
        playerService.setActivity(activity);
    }

    public void setDirection(Direction direction){
        Objects.requireNonNull(direction, "Cannot set the player direction");
        playerService.setDirection(direction);
    }
}
