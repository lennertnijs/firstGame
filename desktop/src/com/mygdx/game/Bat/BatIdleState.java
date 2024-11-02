package com.mygdx.game.Bat;

import com.mygdx.game.UpdatedUtil.Vec2;

import java.util.Objects;

public final class BatIdleState implements MonsterState{

    /**
     * The bat.
     */
    private final Monster bat;
    /**
     * The delta count since the last move.
     */
    private double deltaSinceLastMove = 0;

    /**
     * Creates a new {@link BatIdleState} with the given bat.
     * @param bat The bat. Cannot be null.
     */
    public BatIdleState(Monster bat){
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

    private void handleStateChange(Vec2 playerPosition){
        int distanceToPlayer = Vec2.distanceBetween(playerPosition, bat.getPosition());
        if(distanceToPlayer <= bat.getAggressionRange()){
            bat.setState(new BatAttackState(bat));
        }
        if(deltaSinceLastMove > 15_000){
            bat.setState(new BatMoveState(bat));
        }
    }
}
