package com.mygdx.game.Bat;

import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;
import com.mygdx.game.UtilMethods.RandomGenerator;

import java.util.Objects;

public final class BatRepositionState implements BatState{

    private final Bat bat;
    private final Point goal;

    public BatRepositionState(Bat bat) {
        this.bat = Objects.requireNonNull(bat, "Bat is null.");
        this.goal = RandomGenerator.generateAround(bat.getPosition(), 100, 250);
    }

    @Override
    public String getName() {
        return "reposition";
    }


    @Override
    public void handle(double delta, Point playerPosition) {
        int movement = (int) (delta * bat.getSpeed() / 300);
        Point current = bat.getPosition();
        if(movement >= Point.distanceBetween(bat.getPosition(), goal)){
            bat.setPosition(goal);
        }else{
            Vector scaledMovementVector = Vector.between(current, goal).scaleToSize(movement);
            Point nextPosition = current.add(scaledMovementVector);
            bat.setPosition(nextPosition);
        }
        handleStateChange();
    }

    private void handleStateChange(){
        boolean arrivedAtGoal = bat.getPosition().equals(goal);
        if(arrivedAtGoal){
            bat.setState(new BatAttackState(bat));
        }
    }
}
