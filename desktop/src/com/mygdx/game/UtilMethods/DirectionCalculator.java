package com.mygdx.game.UtilMethods;

import com.mygdx.game.Util.Direction;
import com.mygdx.game.UpdatedUtil.Vec2;

public final class DirectionCalculator {

    private DirectionCalculator(){

    }

    public static Direction calculateDir(Vec2 start, Vec2 end){
        int x_diff = Math.abs(start.x() - end.x());
        int y_diff = Math.abs(start.y() - end.y());
        if(start.x() < end.x() && x_diff >= y_diff){
            return Direction.RIGHT;
        }
        if(start.x() > end.x() && x_diff >= y_diff){
            return Direction.LEFT;
        }
        if(start.y() < end.y()){
            return Direction.UP;
        }
        return Direction.DOWN;
    }
}
