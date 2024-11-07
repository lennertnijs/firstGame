package com.mygdx.game.quad_tree;

import com.mygdx.game.util.Vec2;

public interface GameObject2D {

    Vec2 getPosition();
    int getWidth();
    int getHeight();
    boolean contains(int x, int y, int width, int height);
    boolean intersects(int x, int y, int width, int height);
}
