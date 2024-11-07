package com.mygdx.game.quad_tree;

import java.util.Objects;

public final class QuadTree<T extends GameObject2D> {

    private final Node<T> root;

    public QuadTree(int x, int y, int width, int height){
        this.root = new Node<>(x, y, width, height);
    }

    public void add(GameObject2D object){
        Objects.requireNonNull(object);
    }
}
