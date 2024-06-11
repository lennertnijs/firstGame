package com.mygdx.game.UtilMethods;

import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;

public final class DirectionCalculator {

    private DirectionCalculator(){

    }

    public static Direction calculate(Point start, Point end){
        Vector v = new Vector(end.x() - start.x(), end.y() - start.y());
        double angle = Math.atan((double) v.y() / v.x());
        if(angle > 315 || angle < 45){
            return Direction.RIGHT;
        }
        if(angle >= 45 && angle <= 135){
            return Direction.UP;
        }
        if(angle > 135 && angle < 225){
            return Direction.LEFT;
        }
        if(angle >= 225){
            return Direction.DOWN;
        }
        throw new IllegalStateException();
    }
}
