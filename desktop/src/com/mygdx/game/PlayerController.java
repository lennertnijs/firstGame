package com.mygdx.game;

import com.mygdx.game.GameObject.Player;
import com.mygdx.game.Keys.ActivityType;
import com.mygdx.game.Util.Direction;

import static com.mygdx.game.Keys.NPCActivityType.WALKING;

public final class PlayerController{

    private final Player player;

    public PlayerController(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

    public void update(double delta, Direction direction){
        player.increaseAnimationDelta(delta);
        if(direction == null){
            if(player.getCurrentActivityType() == WALKING){
                player.removeCurrentActivityType();
            }
            return;
        }
        movePlayer(delta, direction);
    }

    public void movePlayer(double delta, Direction direction){
        player.move(delta, direction);
        player.setDirection(direction);
    }

    public void setCurrentActivity(ActivityType type){
        player.storeActivityType(type);
    }

    public void removeCurrentActivity(){
        player.removeCurrentActivityType();
    }
}
