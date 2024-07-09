package com.mygdx.game.Bat;

import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;
import com.mygdx.game.UtilMethods.RandomNumberGenerator;

import java.util.Objects;

public final class BatMoveState implements BatState{

    private final Bat bat;
    private final Point goal;

    public BatMoveState(Bat bat){
        this.bat = Objects.requireNonNull(bat, "Bat is null.");
        this.goal = generateGoalPoint();
    }

    private Point generateGoalPoint(){
        int x_extra = RandomNumberGenerator.generateBetween(-250, 250);
        int y_extra = RandomNumberGenerator.generateBetween(-250, 250);
        Point currentPosition = bat.getPosition();
        return new Point(currentPosition.x() + x_extra, currentPosition.y() + y_extra);
    }

    @Override
    public void handle(double delta, Point playerPosition) {
        int movement = (int) (delta/1000 * bat.getSpeed());
        Point current = bat.getPosition();
        if(movement >= Point.distanceBetween(bat.getPosition(), goal)){
            bat.setPosition(goal);
        }else{
            Vector movementVector = new Vector(goal.x() - current.x(), goal.y() - current.y());
            Vector scaledMovementVector = movementVector.scaleToSize(movement);
            Point next = current.add(scaledMovementVector);
            bat.setPosition(next);
        }
        handleStateChange(playerPosition);
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
