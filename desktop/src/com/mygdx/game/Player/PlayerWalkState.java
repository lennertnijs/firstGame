package com.mygdx.game.Player;

import com.mygdx.game.HitBox.HitBox;
import com.mygdx.game.HitBox.Rectangle;
import com.mygdx.game.HitBoxSnapShot;
import com.mygdx.game.Util.Vec2;
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

    public void progress(double delta, Direction direction, HitBoxSnapShot snapShot){
        int movement = (int) (delta * player.getSpeed() / 250);
        Vec2 next = MovementUtilMethods.calculateNextPosition(player.getPosition(), direction, movement);
        HitBox hitBox = new Rectangle(next, player.getDimensions());
        player.setDirection(direction);
        if(snapShot.isFree(hitBox)){
            player.setPosition(next);
        }
    }
}
