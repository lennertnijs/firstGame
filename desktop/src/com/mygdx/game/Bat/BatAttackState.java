package com.mygdx.game.Bat;

import com.mygdx.game.Util.Point;

import java.util.Objects;

public final class BatAttackState implements BatState{

    private final Bat bat;

    public BatAttackState(Bat bat){
        this.bat = Objects.requireNonNull(bat, "Bat is null.");
    }

    @Override
    public void handle(double delta, Point playerPosition) {

    }
}
