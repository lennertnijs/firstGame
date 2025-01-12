package game.util;

import game.npc.navigation.Location;

import java.util.List;

public final class MovementUtilMethods {

    private MovementUtilMethods(){
    }

    public static Location moveAlongRoute(Location start, List<Location> route, int amount){
        if(amount <= 0 || route.isEmpty()){
            return start;
        }
        Location goal = route.get(0);
        int x_diff = goal.position().x() - start.position().x();
        int y_diff = goal.position().y() - start.position().y();
        int distanceBetweenPoints = (int) Math.sqrt(x_diff * x_diff + y_diff * y_diff);
        if (amount < distanceBetweenPoints) {
            Vec2 vector = new Vec2(x_diff, y_diff).scaleToSize(amount);
            return new Location(start.map(), new Vec2(start.position().x() + vector.x(), start.position().y() + vector.y()));
        }else{
            route.remove(0);
            return moveAlongRoute(goal, route, amount -  start.position().distanceTo(goal.position()));
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
}
