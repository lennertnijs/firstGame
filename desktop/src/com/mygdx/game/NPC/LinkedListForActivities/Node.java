package com.mygdx.game.NPC.LinkedListForActivities;

import com.mygdx.game.NPC.Activity;

 public class Node {

    private final Activity activity;
    private Node next = null;

    /**
     * Constructor for a {@code Node} for a singly linked list
     * @param activity The activity
     */
    public Node(Activity activity){
        this.activity = activity;
    }

    public Activity getActivity(){
        return this.activity;
    }


    public boolean hasNext(){
        return this.next != null;
    }

    public Node next(){
        if(this.next == null){
            throw new IllegalArgumentException("Cannot go to the next node as it is null");
        }
        return this.next;
    }

    public void setNext(Node node){
        if(node == null){
            throw new IllegalArgumentException("Cannot set the next node to a null node.");
        }
        this.next = node;
    }

}
