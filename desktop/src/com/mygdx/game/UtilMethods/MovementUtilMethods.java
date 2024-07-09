package com.mygdx.game.UtilMethods;

import com.mygdx.game.Navigation.Route;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Location;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;

import java.util.Objects;

public final class MovementUtilMethods {

    private MovementUtilMethods(){
    }

    public static Location moveAlongRoute(Location start, Route route, int amount){
        if(amount <= 0 || route.isEmpty()){
            return start;
        }
        Location goal = route.peek();
        int x_diff = goal.x() - start.x();
        int y_diff = goal.y() - start.y();
        int distanceBetweenPoints = (int) Math.sqrt(x_diff * x_diff + y_diff * y_diff);
        if (amount < distanceBetweenPoints) {
            Vector vector = new Vector(x_diff, y_diff).scaleToSize(amount);
            return new Location(start.mapName(), new Point(start.x() + vector.x(), start.y() + vector.y()));
        }else{
            return moveAlongRoute(route.poll(), route, amount -  Point.distanceBetween(start.position(), goal.position()));
        }
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