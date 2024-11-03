package com.mygdx.game.monster.bat;

import com.mygdx.game.monster.Monster;
import com.mygdx.game.monster.MonsterState;
import com.mygdx.game.util.RandomGenerator;
import com.mygdx.game.util.Vec2;

import java.util.Objects;

public final class BatRepositionState implements MonsterState {

    private final Monster monster;
    private final Vec2 goal;

    public BatRepositionState(Monster monster) {
        this.monster = Objects.requireNonNull(monster, "monster is null.");
        this.goal = RandomGenerator.generateAround(monster.getPosition(), 100, 250);
    }

    @Override
    public String getName() {
        return "reposition";
    }


    @Override
    public void update(double delta, Vec2 playerPosition) {
        int movement = (int) (delta * monster.getStats().getSpeed() / 300);
        Vec2 current = monster.getPosition();
        if(movement >= Vec2.distanceBetween(monster.getPosition(), goal)){
            monster.setPosition(goal);
        }else{
            Vec2 scaledMovementVector = Vec2.createBetween(current, goal).scaleToSize(movement);
            Vec2 nextPosition = current.add(scaledMovementVector);
            monster.setPosition(nextPosition);
        }
        handleStateChange();
    }

    private void handleStateChange(){
        boolean arrivedAtGoal = monster.getPosition().equals(goal);
        if(arrivedAtGoal){
            monster.changeState(new BatAttackState(monster));
        }
    }
}
