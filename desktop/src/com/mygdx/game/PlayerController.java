package com.mygdx.game;

import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.HitBox.HitBox;
import com.mygdx.game.HitBox.Rectangle;
import com.mygdx.game.Input.MovementInputs;
import com.mygdx.game.Inventory.Tool;
import com.mygdx.game.Player.*;
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
        if(!player.hasToolInActive()){
            return;
        }
        Tool activeTool = (Tool) player.getActiveItem();
        if(!player.getState().equals("idle") && !player.getState().equals("move")){
            return;
        }
        switch(activeTool.type()){
            case PICKAXE: player.changeState(new PlayerMineState(player)); break;
            case AXE: player.changeState(new PlayerAxeState(player)); break;
            case SWORD: player.changeState(null); break;
            case SHOVEL: player.changeState(null); break;
        }
        if(breakable == null){
            return;
        }
        if(player.getFrame("temp", player.getDirection()).damageBox() != null){
            Vector translation = player.getFrame("temp", player.getDirection()).damageBoxTranslation();
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
