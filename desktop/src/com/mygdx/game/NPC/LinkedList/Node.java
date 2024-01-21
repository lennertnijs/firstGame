package com.mygdx.game.NPC.LinkedList;

import com.mygdx.game.NPC.Activity;

public class Node {

    private final String time;
    private final Activity activity;
    private Node nextNode = null;

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

    public Node getNextNode(){
        return this.nextNode;
    }

    /**
     * Changes the next-node reference of this node
     * @param node The next node. Cannot be null
     */
    public void setNextNode(Node node){
        if(node == null){
            throw new IllegalArgumentException("Cannot reference a null node.");
        }
        this.nextNode = node;
    }

    /**
     * @return True if the current node has a next node, false otherwise
     */
    public boolean hasNext(){
        if(this.nextNode != null){
            return true;
        }
        return false;
    }


}
