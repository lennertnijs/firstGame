package com.mygdx.game.util;

import com.mygdx.game.npc.navigation.Route;
import com.mygdx.game.game_object.renderer.Direction;
import com.mygdx.game.npc.navigation.Location;

import java.util.Objects;

public final class MovementUtilMethods {

    private MovementUtilMethods(){
    }

    public static Location moveAlongRoute(Location start, Route route, int amount){
        if(amount <= 0 || route.isEmpty()){
            return start;
        }
        Location goal = route.peek();
        int x_diff = goal.position().x() - start.position().x();
        int y_diff = goal.position().y() - start.position().y();
        int distanceBetweenPoints = (int) Math.sqrt(x_diff * x_diff + y_diff * y_diff);
        if (amount < distanceBetweenPoints) {
            Vec2 vector = new Vec2(x_diff, y_diff).scaleToSize(amount);
            return new Location(start.map(), new Vec2(start.position().x() + vector.x(), start.position().y() + vector.y()));
        }else{
            return moveAlongRoute(route.poll(), route, amount -  start.position().distanceTo(goal.position()));
        }
    }

    public static Vec2 moveToPoint(Vec2 start, Vec2 goal, int amount){
        if(amount <= 0){
            return start;
        }
        int x_diff = goal.x() - start.x();
        int y_diff = goal.y() - start.y();
        int distanceBetweenPoints = start.distanceTo(goal);
        if(distanceBetweenPoints <= amount){
            return goal;
        }
        Vec2 vector = new Vec2(x_diff, y_diff).scaleToSize(amount);
        return new Vec2(start.x() + vector.x(), start.y() + vector.y());
    }

    public static Vec2 calculateNextPosition(Vec2 start, Direction direction, int amount){
        Objects.requireNonNull(start, "Start point is null.");
        Objects.requireNonNull(direction, "Direction is null.");
        if(amount < 0){
            throw new IllegalArgumentException("Amount is negative.");
        }
        return switch (direction) {
            case UP -> new Vec2(start.x(), start.y() + amount);
            case RIGHT -> new Vec2(start.x() + amount, start.y());
            case DOWN -> new Vec2(start.x(), start.y() - amount);
            case LEFT -> new Vec2(start.x() - amount, start.y());
        };
    }
}
