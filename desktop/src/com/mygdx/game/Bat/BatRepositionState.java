package com.mygdx.game.Bat;

import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;
import com.mygdx.game.UtilMethods.RandomNumberGenerator;

import java.util.Objects;

public final class BatRepositionState implements BatState{

    private final Bat bat;
    private final Point goal;

    public BatRepositionState(Bat bat){
        this.bat = Objects.requireNonNull(bat, "Bat is null.");
        this.goal = generateRepositionPoint();
    }

    private Point generateRepositionPoint(){
        int x_extra = RandomNumberGenerator.generateBetween(-150, 150);
        int y_extra = RandomNumberGenerator.generateBetween(-150, 150);
        Point currentPosition = bat.getPosition();
        return new Point(currentPosition.x() + x_extra, currentPosition.y() + y_extra);
    }


    @Override
    public void handle(double delta, Point playerPosition) {
        int movement = (int) (delta * bat.getSpeed() / 100);
        Point current = bat.getPosition();
        if(movement >= Point.distanceBetween(bat.getPosition(), goal)){
            bat.setPosition(goal);
        }else{
            Vector movementVector = new Vector(goal.x() - current.x(), goal.y() - current.y());
            Vector scaledMovementVector = movementVector.scaleToSize(movement);
            Point next = current.add(scaledMovementVector);
            bat.setPosition(next);
        }
        handleStateChange();
    }

    @Override
    public String getState() {
        return "reposition";
    }

    private void handleStateChange(){
        boolean arrivedAtGoal = bat.getPosition().equals(goal);
        if(arrivedAtGoal){
            bat.setState(new BatAttackState(bat));
        }
    }
}
