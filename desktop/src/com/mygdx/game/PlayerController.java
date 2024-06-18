package com.mygdx.game;

import com.mygdx.game.Player.Player;
import com.mygdx.game.Input.MovementInputs;
import com.mygdx.game.Player.PlayerState;
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

    public void changePlayerState(PlayerState state){
        player.changeState(state);
    }

    public MovementInputs getMovementFlags() {
        return movementFlags;
    }

    public void update(double delta, HitBoxSnapShot snapShot){
        player.increaseAnimationDelta(delta);
        Direction direction = movementFlags.getCurrentDirection();
        if(direction == null){
            return;
        }
        player.update(delta, direction, snapShot);
    }

    public void useActiveItem(){
        // set the correct state
        player.useActiveItem(null);
    }

    public void setNextActive(){
        player.incrementActiveIndex();
    }
}
