package com.mygdx.game.NPC.LinkedList;

import com.mygdx.game.NPC.Activity;

 public class Node {

    private final String time;
    private final Activity activity;
    private Node next = null;

    /**
     * Constructor for a {@code Node} for a singly linked list
     * @param time The start time of the {@code Activity}
     * @param activity The activity
     */
    public Node(String time, Activity activity){
        this.time = time;
        this.activity = activity;
    }

    public String getTime(){
        return this.time;
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
