package com.mygdx.game.GameClock;

public interface TimeProvider {

    double getDelta();
    void update();
    void reset();
}
