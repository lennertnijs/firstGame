package game.monster.bat;

import game.monster.Monster;
import game.monster.MonsterState;
import game.util.Vec2;

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
        int distanceToPlayer = monster.getPosition().distanceTo(playerPosition);
        if(distanceToPlayer <= monster.getStats().aggressionRange()){
            monster.changeState(new BatAttackState(monster));
        }
        if(deltaSinceLastMove > 15_000){
            monster.changeState(new BatMoveState(monster));
        }
    }
}
