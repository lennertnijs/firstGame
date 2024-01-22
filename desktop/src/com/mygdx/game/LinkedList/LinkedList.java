package com.mygdx.game.LinkedList;

import static com.mygdx.game.ArgumentValidator.ifNullThrowError;
import static com.mygdx.game.ArgumentValidator.isNull;

// TESTED
public class LinkedList {

    private Node head = null;
    public LinkedList(){
    }

    public Node getHead(){
        return this.head;
    }

    public int size(){
        if(isNull(head)){
            return 0;
        }
        Node current = head;
        int size = 1;
        while(current.hasNext() && !isHeadNode(current.next())){
            size += 1;
            current = current.next();
        }
        return size;
    }

    /**
     * Adds the {@code Node} at the end of the DLL
     */
    public void add(Node node){
        ifNullThrowError(node, "Cannot add a null node to a DLL");
        boolean emptyLinkedList = isNull(head);
        if(emptyLinkedList){
            head = node;
            return;
        }
        Node current = head;
        while(current.hasNext()){
            current = current.next();
        }
        current.setNext(node);
        node.setPrev(current);
    }



    private boolean isHeadNode(Node node){
        return head.equals(node);
    }
}
