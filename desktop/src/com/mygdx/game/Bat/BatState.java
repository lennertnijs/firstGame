package com.mygdx.game.Bat;

import com.mygdx.game.UpdatedUtil.Vec2;

public interface BatState {

    /**
     * @return The state's name.
     */
    String getName();

    /**
     * Handles the update.
     */
    void handle(double delta, Vec2 playerPosition);
}
