package game.monster.bat;

import game.monster.Monster;
import game.monster.MonsterState;
import game.util.Vec2;
import game.util.MovementUtilMethods;

import java.util.Objects;

public final class BatAttackState implements MonsterState {

    private final Monster monster;

    public BatAttackState(Monster monster){
        this.monster = Objects.requireNonNull(monster);
    }

    @Override
    public String getName() {
        return "attack";
    }

    /**
     * Handles the update in the attack state.
     * More precisely, calculates the amount of movement using the delta and the bat's speed, then uses
     * this to move from its current position towards the player's position for an attack.
     * Handles a possible state change after wards.
     * @param delta The delta. Cannot be negative.
     * @param playerPosition The player position. Cannot be null.
     */
    @Override
    public void update(double delta, Vec2 playerPosition) {
        int movement = (int)(delta * monster.getStats().getSpeed() / 200);
        Vec2 next = MovementUtilMethods.moveToPoint(monster.getPosition(), playerPosition, movement);
        monster.setPosition(next);
        handleStateChange(playerPosition);
    }

    /**
     * Handles a possible state change.
     * More precisely, if the bat collided with the player, sets the bat's state to the {@link BatRepositionState}.
     * @param playerPosition The player position. Cannot be null.
     */
    private void handleStateChange(Vec2 playerPosition){
//        if(bat.getHitBox().contains(playerPosition)){
//            bat.setState(new BatRepositionState(bat));
//        }
    }
}
