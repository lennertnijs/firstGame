package com.mygdx.game.Bat;

import com.mygdx.game.UpdatedUtil.Vec2;
import com.mygdx.game.UtilMethods.MovementUtilMethods;

import java.util.Objects;

public final class BatAttackState implements MonsterState{

    /**
     * The bat.
     */
    private final Monster bat;

    /**
     * Creates a new {@link BatAttackState} with the given bat.
     * @param bat The bat. Cannot be null.
     */
    public BatAttackState(Monster bat){
        this.bat = Objects.requireNonNull(bat, "Bat is null.");
    }

    /**
     * {@inheritDoc}
     */
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
    public void handle(double delta, Vec2 playerPosition) {
        int movement = (int)(delta * bat.getSpeed() / 200);
        Vec2 next = MovementUtilMethods.moveToPoint(bat.getPosition(), playerPosition, movement);
        bat.setPosition(next);
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
