package com.mygdx.game.Util;

import java.util.Objects;

public final class MovementUtil {

    private MovementUtil(){
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
}
