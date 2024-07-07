package com.mygdx.game;

import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.Input.MovementInputs;
import com.mygdx.game.Player.IdlePlayerState;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Player.PlayerOtherState;
import com.mygdx.game.Player.PlayerWalkState;
import com.mygdx.game.Util.Direction;

public final class PlayerController{

    private final Player player;
    private final MovementInputs movementFlags = new MovementInputs();

    public PlayerController(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }


    public void addDirection(Direction direction){
        if(movementFlags.getCurrentDirection() == null){
            player.changeState(new PlayerWalkState(player));
        }
        movementFlags.addDirection(direction);
    }

    public void removeDirection(Direction direction){
        movementFlags.removeDirection(direction);
        if(movementFlags.getCurrentDirection() == null){
            player.changeState(new IdlePlayerState(player));
        }
    }

    public void update(double delta, HitBoxSnapShot snapShot){
        player.increaseAnimationDelta(delta);
        Direction direction = movementFlags.getCurrentDirection();
        player.update(delta, direction, snapShot);
    }

    public void useActiveItem(Breakable breakable){
        if(player.getState().equals("mine")){
            return;
        }
        player.changeState(new PlayerOtherState(player));
        if(breakable != null){
            player.useActiveItem(breakable);
        }
    }

    public void setNextActive(){
        player.incrementActiveIndex();
    }
}
