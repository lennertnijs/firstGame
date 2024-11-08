package com.mygdx.game.r_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Node<T extends GameObject2D> {

    private final Rectangle area;
    private final List<T> objects;
    private final List<Node<T>> children;

    public Node(int x, int y, int width, int height){
        this.area = new Rectangle(x, y, width, height);
        this.objects = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public Rectangle getRectangle(){
        return area;
    }

    public int getArea(){
        return area.width() * area.height();
    }

    public int getPerimeter(){
        return 2 * area.width() + 2 * area.height();
    }

    public List<T> getObjects(){
        return objects;
    }

    public List<Node<T>> getChildren(){
        return children;
    }

    public boolean contains(Rectangle r){
        Objects.requireNonNull(r);
        return r.x() >= area.x() && r.x() + r.width() < area.x() + area.width() &&
                r.y() >= area.y() && r.y() + r.height() < area.y() + area.height();
    }

    public boolean isLeaf(){
        return children.size() == 0;
    }

    public void addObject(T object){
        if(!isLeaf()){
            throw new IllegalStateException();
        }
        objects.add(Objects.requireNonNull(object));
    }
}
