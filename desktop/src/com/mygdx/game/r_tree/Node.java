package com.mygdx.game.r_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Node<T extends GameObject2D> {

    private Rectangle rectangle;
    private final List<T> objects;
    private Node<T> parent;
    private final List<Node<T>> children;

    public Node(){
        this.rectangle = new Rectangle(0, 0, 0, 0);
        this.objects = new ArrayList<>();
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

    public int getPerimeter(){
        return 2 * rectangle.width() + 2 * rectangle.height();
    }

    public int getArea(){
        return rectangle.width() * rectangle.height();
    }

    public void updateRectangle(){
        this.rectangle = Rectangle.createMinimumBounding(objects.stream().map(GameObject2D::getRectangle).collect(Collectors.toList()));
    }

    public List<T> getObjects(){
        return objects;
    }

    public void addObject(T object){
        if(children.size() != 0){
            throw new IllegalStateException("Cannot store objects in internal nodes.");
        }
        objects.add(Objects.requireNonNull(object));
        updateRectangle();
    }

    public Node<T> getParent(){
        return parent;
    }

    public List<Node<T>> getChildren(){
        return children;
    }

    public void addChild(Node<T> node){
        this.children.add(Objects.requireNonNull(node));
        node.parent = this;
    }

    public boolean isLeaf(){
        return children.size() == 0;
    }
}
