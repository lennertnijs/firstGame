package com.mygdx.game.player.states;

public interface PlayerState {

    /**
     * Updates the current state with the given delta.
     */
    void update(double delta);

    String getActivityName();
}
