package com.mygdx.game.monster.bat;

import com.mygdx.game.monster.Monster;
import com.mygdx.game.monster.MonsterState;
import com.mygdx.game.util.RandomGenerator;
import com.mygdx.game.util.Vec2;

import java.util.Objects;

public final class BatMoveState implements MonsterState {

    private final Monster bat;
    private final Vec2 goal;

    public BatMoveState(Monster bat){
        this.bat = Objects.requireNonNull(bat, "monster is null.");
        this.goal = RandomGenerator.generateAround(bat.getPosition(), 150, 250);
    }

    @Override
    public void update(double delta, Vec2 playerPosition) {
        int movement = (int) (delta * bat.getStats().getSpeed() / 200);
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
        if(distanceToPlayer <= bat.getStats().aggressionRange()){
            bat.changeState(new BatAttackState(bat));
        }
        boolean arrivedAtGoal = bat.getPosition().equals(goal);
        if(arrivedAtGoal){
            bat.changeState(new BatIdleState(bat));
        }
    }
}
