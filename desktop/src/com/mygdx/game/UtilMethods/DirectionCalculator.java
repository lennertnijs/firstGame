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
        if(angle > -Math.PI/4 && angle < Math.PI/4){
            return Direction.RIGHT;
        }
        if(angle >= Math.PI/4 && angle <= 3*Math.PI/4){
            return Direction.UP;
        }
        if(angle > 3*Math.PI/4 && angle <= Math.PI || angle < -3*Math.PI/4 && angle >= -Math.PI || angle == -0){
            return Direction.LEFT;
        }
        if(angle <= -Math.PI/4 || angle >= -3*Math.PI/4){
            return Direction.DOWN;
        }
        throw new IllegalStateException();
    }
}
