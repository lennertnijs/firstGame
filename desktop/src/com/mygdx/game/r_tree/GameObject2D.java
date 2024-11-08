package com.mygdx.game.r_tree;

public interface GameObject2D {

    Rectangle getRectangle();
    boolean contains(int x, int y, int width, int height);
    boolean intersects(int x, int y, int width, int height);
}
