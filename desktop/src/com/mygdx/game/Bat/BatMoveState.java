package com.mygdx.game.Bat;

import com.mygdx.game.UpdatedUtil.Vec2;
import com.mygdx.game.UtilMethods.RandomGenerator;

import java.util.Objects;

public final class BatMoveState implements MonsterState{

    private final Monster bat;
    private final Vec2 goal;

    public BatMoveState(Monster bat){
        this.bat = Objects.requireNonNull(bat, "Bat is null.");
        this.goal = RandomGenerator.generateAround(bat.getPosition(), 150, 250);
    }

    @Override
    public void handle(double delta, Vec2 playerPosition) {
        int movement = (int) (delta * bat.getSpeed() / 200);
        Vec2 current = bat.getPosition();
        if(movement >= Vec2.distanceBetween(current, goal)){
            bat.setPosition(goal);
        }else{
            Vec2 scaledMovementVector = Vec2.createBetween(current, goal).scaleToSize(movement);
            Vec2 nextPosition = current.add(scaledMovementVector);
            bat.setPosition(nextPosition);
        }
        handleStateChange(playerPosition);
    }

    @Override
    public String getName() {
        return "move";
    }

    private void handleStateChange(Vec2 playerPosition){
        int distanceToPlayer = Vec2.distanceBetween(bat.getPosition(), playerPosition);
        if(distanceToPlayer <= bat.getAggressionRange()){
            bat.setState(new BatAttackState(bat));
        }
        boolean arrivedAtGoal = bat.getPosition().equals(goal);
        if(arrivedAtGoal){
            bat.setState(new BatIdleState(bat));
        }
    }
}
