package com.mygdx.game.Bat;

import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;
import com.mygdx.game.UtilMethods.RandomGenerator;

import java.util.Objects;

public final class BatMoveState implements BatState{

    private final Bat bat;
    private final Point goal;

    public BatMoveState(Bat bat){
        this.bat = Objects.requireNonNull(bat, "Bat is null.");
        this.goal = RandomGenerator.generateAround(bat.getPosition(), 150, 250);
    }

    @Override
    public void handle(double delta, Point playerPosition) {
        int movement = (int) (delta * bat.getSpeed() / 200);
        Point current = bat.getPosition();
        if(movement >= Point.distanceBetween(current, goal)){
            bat.setPosition(goal);
        }else{
            Vector scaledMovementVector = Vector.between(current, goal).scaleToSize(movement);
            Point nextPosition = current.add(scaledMovementVector);
            bat.setPosition(nextPosition);
        }
        handleStateChange(playerPosition);
    }

    @Override
    public String getName() {
        return "move";
    }

    private void handleStateChange(Point playerPosition){
        int distanceToPlayer = Point.distanceBetween(bat.getPosition(), playerPosition);
        if(distanceToPlayer <= bat.aggressionRange()){
            bat.setState(new BatAttackState(bat));
        }
        boolean arrivedAtGoal = bat.getPosition().equals(goal);
        if(arrivedAtGoal){
            bat.setState(new BatIdleState(bat));
        }
    }
}
