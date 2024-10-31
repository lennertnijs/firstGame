package com.mygdx.game.Player;

import com.mygdx.game.UpdatedUtil.Vec2;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.UtilMethods.MovementUtilMethods;

public class PlayerWalkState implements PlayerState{

    private final Player player;

    public PlayerWalkState(Player player){
        this.player = player;
    }

    @Override
    public String getName() {
        return "walk";
    }

    public void progress(double delta, Direction direction){
        int movement = (int) (delta * player.getSpeed() / 250);
        Vec2 next = MovementUtilMethods.calculateNextPosition(player.getPosition(), direction, movement);
        Dimensions dimensions = new Dimensions(player.getWidth(), player.getHeight());
        player.setDirection(direction);
//        if(snapShot.isFree(hitBox)){
//            player.setPosition(next);
//        }
    }
}
