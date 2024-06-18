package com.mygdx.game.Player;

import com.mygdx.game.HitBoxSnapShot;
import com.mygdx.game.Util.Direction;

public interface PlayerState {

    String getState();
    void progress(double delta, Direction direction, HitBoxSnapShot snapShot);
}
