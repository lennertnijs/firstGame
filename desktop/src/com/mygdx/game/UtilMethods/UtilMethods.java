package com.mygdx.game.UtilMethods;

import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;

import java.util.Objects;

public final class UtilMethods {

    private UtilMethods(){
    }

    public static Point calculateNextPosition(Point current, Point goal, int amountToMove){
        Objects.requireNonNull(current, "Current position is null.");
        Objects.requireNonNull(goal, "Goal position is null.");
        if(amountToMove <= 0){
            throw new IllegalArgumentException("Amount to move is negative or 0.");
        }
        int x_diff = goal.x() - current.x();
        int y_diff = goal.y() - current.y();
        int distanceBetweenPoints = (int) Math.sqrt(x_diff * x_diff + y_diff * y_diff);
        if(amountToMove >= distanceBetweenPoints){
            return goal;
        }
        Vector movement = new Vector(x_diff, y_diff).scaleToSize(amountToMove);
        return current.add(movement);
    }

    public static Point calculateNextPosition(Point start, Direction direction, int amount){
        Objects.requireNonNull(start, "Start point is null.");
        Objects.requireNonNull(direction, "Direction is null.");
        if(amount < 0){
            throw new IllegalArgumentException("Amount is negative.");
        }
        Point next = null;
        switch(direction){
            case UP: next = new Point(start.x(), start.y() + amount); break;
            case RIGHT: next = new Point(start.x() + amount, start.y()); break;
            case DOWN: next = new Point(start.x(), start.y() - amount); break;
            case LEFT: next = new Point(start.x() - amount, start.y()); break;
        }
        return next;
    }
}
