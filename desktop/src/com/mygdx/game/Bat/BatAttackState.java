package com.mygdx.game.Bat;

import com.mygdx.game.Util.Point;
import com.mygdx.game.UtilMethods.MovementUtilMethods;

import java.util.Objects;

public final class BatAttackState implements BatState{

    private final Bat bat;

    public BatAttackState(Bat bat){
        this.bat = Objects.requireNonNull(bat, "Bat is null.");
    }

    @Override
    public void handle(double delta, Point playerPosition) {
        int movement = (int)(delta/1000 * bat.getSpeed());
        Point next = MovementUtilMethods.moveToPoint(bat.getPosition(), playerPosition, movement);
        bat.setPosition(next);
        handleStateChange(playerPosition);
    }

    @Override
    public String getState() {
        return "attack";
    }

    private void handleStateChange(Point playerPosition){
        if(bat.getHitBox().contains(playerPosition)){
            bat.setState(new BatRepositionState(bat));
        }
    }
}
