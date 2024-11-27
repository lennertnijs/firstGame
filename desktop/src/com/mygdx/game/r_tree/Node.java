package com.mygdx.game.r_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Node(List<T> objects){
        Objects.requireNonNull(objects);
        if(objects.contains(null)){
            throw new NullPointerException();
        }
        this.objects = new ArrayList<>(objects);
        this.parent = null;
        this.children = new ArrayList<>();
        this.rectangle = Rectangle.createMinimumBounding(objects.stream().map(GameObject2D::getRectangle).toList());
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
        if(this.isInternal()){
            this.rectangle = Rectangle.createMinimumBounding(children.stream().map(Node::getRectangle).toList());
        }else {
            this.rectangle = Rectangle.createMinimumBounding(objects.stream().map(GameObject2D::getRectangle).toList());
        }
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

    public void setParent(Node<T> parent){
        this.parent = parent;
    }

    public List<Node<T>> getChildren(){
        return children;
    }

    public void remove(Node<T> node){
        this.children.remove(node);
    }

    public void addChild(Node<T> node){
        this.children.add(Objects.requireNonNull(node));
        node.parent = this;
        updateRectangle();
    }

    public void addChildren(List<Node<T>> children){
        for(Node<T> child : children){
            addChild(child);
        }
    }

    public void addObjects(List<T> objects){
        this.objects.addAll(objects);
        updateRectangle();
    }

    public boolean isLeaf(){
        return children.isEmpty();
    }

    public boolean isRoot(){
        return this.parent == null;
    }

    public boolean isInternal(){
        return !children.isEmpty();
    }
}
