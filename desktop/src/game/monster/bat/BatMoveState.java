package game.monster.bat;

import game.monster.Monster;
import game.monster.MonsterState;
import game.util.RandomGenerator;
import game.util.Vec2;

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
        if(movement >= current.distanceTo(goal)){
            bat.setPosition(goal);
        }else{
            Vec2 scaledMovementVector = Vec2.createFromTo(current, goal).scaleToSize(movement);
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
        int distanceToPlayer = bat.getPosition().distanceTo(playerPosition);
        if(distanceToPlayer <= bat.getStats().aggressionRange()){
            bat.changeState(new BatAttackState(bat));
        }
        boolean arrivedAtGoal = bat.getPosition().equals(goal);
        if(arrivedAtGoal){
            bat.changeState(new BatIdleState(bat));
        }
    }
}
