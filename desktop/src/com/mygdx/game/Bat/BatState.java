package com.mygdx.game.Bat;

import com.mygdx.game.Util.Point;

public interface BatState {

    /**
     * @return The state's name.
     */
    String getName();

    /**
     * Handles the update.
     */
    void handle(double delta, Point playerPosition);
}
