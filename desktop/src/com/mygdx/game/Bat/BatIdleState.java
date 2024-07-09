package com.mygdx.game.Bat;

import com.mygdx.game.Util.Point;

import java.util.Objects;

public class BatIdleState implements BatState{

    private final Bat bat;
    private double deltaSinceLastMove = 0;

    public BatIdleState(Bat bat){
        this.bat = Objects.requireNonNull(bat, "Bat is null.");
    }

    @Override
    public void handle(double delta, Point playerPosition) {
        this.deltaSinceLastMove += delta;
        handleStateChange(playerPosition);
    }

    @Override
    public String getState() {
        return "idle";
    }

    private void handleStateChange(Point playerPosition){
        int distanceToPlayer = Point.distanceBetween(playerPosition, bat.getPosition());
        if(distanceToPlayer <= bat.aggressionRange()){
            bat.setState(new BatAttackState(bat));
        }
        if(deltaSinceLastMove > 30_000){
            bat.setState(new BatMoveState(bat));
        }
    }
}
