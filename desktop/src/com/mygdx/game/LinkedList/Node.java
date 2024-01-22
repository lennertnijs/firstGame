package com.mygdx.game.LinkedList;

// TESTED

import static com.mygdx.game.ArgumentValidator.ifNullThrowError;

public class Node {

    private Node next = null;
    private Node prev = null;

    public Node(){
    }

    public boolean hasNext(){
        return this.next != null;
    }
    public boolean hasPrev(){
        return this.prev != null;
    }

    public Node next(){
        return this.next;
    }

    public Node prev(){
        return this.prev;
    }

    public void setNext(Node node){
        ifNullThrowError(node, "Cannot reference a null node as next");
        this.next = node;
    }

    public void setPrev(Node node){
        ifNullThrowError(node, "Cannot reference a null node as prev");
        this.prev = node;
    }

    public void removeNext(){
        this.next = null;
    }

    public void removePrev(){
        this.prev = null;
    }
}
