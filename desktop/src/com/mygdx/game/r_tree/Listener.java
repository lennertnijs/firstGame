package com.mygdx.game.r_tree;

import com.mygdx.game.util.Vec2;

public interface Listener {

    boolean isFree(Vec2 position, int width, int height);
}
