package com.mygdx.game.r_tree;

public interface IRTree<T extends GameObject2D> {

    int height();
    int size();
    int maxEntries();
    boolean isFree(int x, int y, int width, int height);
    void insert(T object);
    void delete(T object);
}
