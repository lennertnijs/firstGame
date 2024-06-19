package com.mygdx.game.Bat;

import com.mygdx.game.Util.Point;

public interface BatState {

    void move(Point playerPosition, double delta);
}
