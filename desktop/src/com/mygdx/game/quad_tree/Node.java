package com.mygdx.game.quad_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Node<T extends GameObject2D> {

    private final int x,y;
    private final int width, height;
    private Node<T> NW, NE, SE, SW;
    private final List<T> objects;

    public Node(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.NW = null;
        this.NE = null;
        this.SE = null;
        this.SW = null;
        this.objects = new ArrayList<>();
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }

    public int width(){
        return width;
    }

    public int height(){
        return height;
    }

    public List<T> getObjects(){
        return objects;
    }

    public void split(){
        int halfWidth = width/2;
        int halfHeight = height/2;

        NW = new Node<>(x, y, halfWidth, halfHeight);
        NE = new Node<>(x + halfWidth, y, halfWidth, halfHeight);
        SE = new Node<>(x, y + halfHeight, halfWidth, halfHeight);
        SW = new Node<>(x + halfWidth, y + halfHeight, halfWidth, halfHeight);

        for(GameObject2D object : objects){
            if(NW.contains(object)){
                // store
                continue;
            }
            if(NE.contains(object)){
                // store
                continue;
            }
            if(SE.contains(object)){
                // store
                continue;
            }
            if(SW.contains(object)){
                //store
                continue;
            }
            throw new RuntimeException();
        }
    }

    public boolean contains(GameObject2D object){
        Objects.requireNonNull(object);
        int object_x = object.getPosition().x();
        int object_y = object.getPosition().y();
        return object_x >= x && object_x + object.getWidth() < x + width &&
                object_y >= y && object_y + object.getHeight() < y + height;
    }

    public void insert(){

    }
}
