package com.mygdx.game.Bat;

import com.mygdx.game.Util.Vec2;

import java.util.Objects;

/**
 * The idle state of the {@link Bat}.
 * In the idle state, the bat will stay in its position.
 *
 * If the player gets in the aggression range of the {@link Bat}, swaps to the {@link BatAttackState}.
 * If the internal delta counter goes over the threshold, swaps to the {@link BatMoveState} to move the idle bat
 * a little bit.
 */
public final class BatIdleState implements BatState{

    /**
     * The bat.
     */
    private final Bat bat;
    /**
     * The delta count since the last move.
     */
    private double deltaSinceLastMove = 0;

    /**
     * Creates a new {@link BatIdleState} with the given bat.
     * @param bat The bat. Cannot be null.
     */
    public BatIdleState(Bat bat){
        this.bat = Objects.requireNonNull(bat, "Bat is null.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "idle";
    }

    /**
     * Handles the update in the idle state.
     * More precisely, adds this frame's delta to the delta count.
     * Handles a possible state change after wards.
     * @param delta The delta. Cannot be negative.
     * @param playerPosition The player position. Cannot be null.
     */
    @Override
    public void handle(double delta, Vec2 playerPosition) {
        this.deltaSinceLastMove += delta;
        handleStateChange(playerPosition);
    }

    /**
     * Handles a possible state change.
     * If the player gets in the aggression range of the {@link Bat}, swaps to the {@link BatAttackState}.
     * If the internal delta counter goes over the threshold, swaps to the {@link BatMoveState} to move the idle bat
     * @param playerPosition The player position. Cannot be null.
     */
    private void handleStateChange(Vec2 playerPosition){
        int distanceToPlayer = Vec2.distanceBetween(playerPosition, bat.getPosition());
        if(distanceToPlayer <= bat.aggressionRange()){
            bat.setState(new BatAttackState(bat));
        }
        if(deltaSinceLastMove > 15_000){
            bat.setState(new BatMoveState(bat));
        }
    }
}
