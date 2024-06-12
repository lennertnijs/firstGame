package com.mygdx.game;

import com.mygdx.game.GameObject.Player;
import com.mygdx.game.Input.MovementInputs;
import com.mygdx.game.Util.Direction;

import static com.mygdx.game.Keys.NPCActivityType.WALKING;

public final class PlayerController{

    private final Player player;
    private final MovementInputs movementFlags = new MovementInputs();

    public PlayerController(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

    public MovementInputs getMovementFlags() {
        return movementFlags;
    }

    public void update(double delta, HitBoxSnapShot snapShot){
        player.increaseAnimationDelta(delta);
        Direction direction = movementFlags.getCurrentDirection();
        if(direction == null){
            if(player.getCurrentActivityType() == WALKING){
                player.removeCurrentActivityType();
            }
            return;
        }
        movePlayer(delta, direction, snapShot);
    }

    public void movePlayer(double delta, Direction direction, HitBoxSnapShot hitBoxSnapShot){
        player.move(delta, direction, hitBoxSnapShot);
        player.setDirection(direction);
    }


    public void removeCurrentActivity(){
        player.removeCurrentActivityType();
    }

    public void useActiveItem(){
        player.useActiveItem(null);
    }

    public void setNextActive(){
        player.incrementActiveIndex();
    }
}
