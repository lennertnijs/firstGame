package com.mygdx.game;

import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.HitBox.HitBox;
import com.mygdx.game.HitBox.Rectangle;
import com.mygdx.game.Input.MovementInputs;
import com.mygdx.game.Player.IdlePlayerState;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Player.PlayerOtherState;
import com.mygdx.game.Player.PlayerWalkState;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;

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
        if(breakable == null){
            return;
        }
        if(player.getFrame(getPlayer().generateEntityKey()).damageBox() != null){
            Vector translation = player.getFrame(getPlayer().generateEntityKey()).damageBoxTranslation();
            Point point = player.getPosition().add(translation);
            HitBox h = new Rectangle(point, new Dimensions(10, 20));
            if(h.overlaps(breakable.getHitBox())){
                player.useActiveItem(breakable);
            }
        }
    }

    public void setNextActive(){
        player.incrementActiveIndex();
    }
}
