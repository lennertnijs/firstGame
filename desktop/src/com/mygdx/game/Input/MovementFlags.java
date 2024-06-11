package com.mygdx.game.Input;

import com.mygdx.game.Util.Direction;

public final class MovementFlags {

    private boolean up;
    private boolean right;
    private boolean down;
    private boolean left;

    public void setFlagUp(boolean flag){
        this.up = flag;
    }

    public void setFlagRight(boolean flag){
        this.right = flag;
    }

    public void setFlagDown(boolean flag){
        this.down = flag;
    }

    public void setFlagLeft(boolean flag){
        this.left = flag;
    }

    public Direction getMovementDirection(){
        if(up && right && down){
            return Direction.RIGHT;
        }
        if(right && down && left){
            return Direction.DOWN;
        }
        if(down && left && up){
            return Direction.LEFT;
        }
        if(left && up && right){
            return Direction.UP;
        }
        if(up && right){
            return Direction.NORTHEAST;
        }
        if(right && down){
            return Direction.SOUTHEAST;
        }
        if(down && left){
            return Direction.SOUTHWEST;
        }
        if(left && up){
            return Direction.NORTHWEST;
        }
        if(up){
            return Direction.UP;
        }
        if(right){
            return Direction.RIGHT;
        }
        if(down){
            return Direction.DOWN;
        }
        if(left){
            return Direction.LEFT;
        }
        return null;
    }
}
