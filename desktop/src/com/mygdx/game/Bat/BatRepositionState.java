package com.mygdx.game.Bat;

import com.mygdx.game.Util.Vec2;
import com.mygdx.game.UtilMethods.RandomGenerator;

import java.util.Objects;

public final class BatRepositionState implements BatState{

    private final Bat bat;
    private final Vec2 goal;

    public BatRepositionState(Bat bat) {
        this.bat = Objects.requireNonNull(bat, "Bat is null.");
        this.goal = RandomGenerator.generateAround(bat.getPosition(), 100, 250);
    }

    @Override
    public String getName() {
        return "reposition";
    }


    @Override
    public void handle(double delta, Vec2 playerPosition) {
        int movement = (int) (delta * bat.getSpeed() / 300);
        Vec2 current = bat.getPosition();
        if(movement >= Vec2.distanceBetween(bat.getPosition(), goal)){
            bat.setPosition(goal);
        }else{
            Vec2 scaledMovementVector = Vec2.createBetween(current, goal).scaleToSize(movement);
            Vec2 nextPosition = current.add(scaledMovementVector);
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
