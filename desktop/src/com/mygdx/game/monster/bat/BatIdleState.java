package com.mygdx.game.monster.bat;

import com.mygdx.game.monster.Monster;
import com.mygdx.game.monster.MonsterState;
import com.mygdx.game.util.Vec2;

import java.util.Objects;

public final class BatIdleState implements MonsterState {

    private final Monster monster;
    private double deltaSinceLastMove;

    public BatIdleState(Monster bat){
        this.monster = Objects.requireNonNull(bat);
        this.deltaSinceLastMove = 0;
    }

    @Override
    public String getName() {
        return "idle";
    }

    @Override
    public void update(double delta, Vec2 playerPosition) {
        this.deltaSinceLastMove += delta;
        handleStateChange(playerPosition);
    }

    private void handleStateChange(Vec2 playerPosition){
        int distanceToPlayer = Vec2.distanceBetween(playerPosition, monster.getPosition());
        if(distanceToPlayer <= monster.getStats().aggressionRange()){
            monster.changeState(new BatAttackState(monster));
        }
        if(deltaSinceLastMove > 15_000){
            monster.changeState(new BatMoveState(monster));
        }
    }
}
