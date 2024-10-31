package com.mygdx.game.Player;

import com.mygdx.game.Util.Direction;

public interface PlayerState {

    String getName();
    void progress(double delta, Direction direction);
}
