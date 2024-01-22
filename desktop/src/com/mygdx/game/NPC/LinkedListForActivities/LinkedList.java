package com.mygdx.game.NPC.LinkedListForActivities;

public class LinkedList {

    private Node startNode = null;
    public LinkedList(){
    }



    public Node getStartNode(){
        return this.startNode;
    }

    /**
     * Adds the node to the end of the linked list.
     * The linked list does not do any sorting, so the elements should be added accordingly.
     * @param node The node to be added to the linked list
     */
    public void add(Node node){
        if(node == null){
            throw new IllegalArgumentException("Cannot add a null node to the linked list.");
        }
        if(startNode == null){
            startNode = node;
            return;
        }
        Node current = startNode;
        while(current.hasNext()){
            current = current.next();
        }
        current.setNext(node);
    }

    /**
     * Finds the node with the given time, if it exists, and returns it. Returns null otherwise.
     * @param time the time of the node that is to be searched for
     * @return The node, or null if no such node exists.
     */
    public Node nodeWithTime(String time){
        if(time == null){
            throw new IllegalArgumentException("Cannot compare a null time.");
        }
        Node currentNode = startNode;
        while(currentNode.hasNext()){
            if(currentNode.getActivity().getTime().equals(time)){
                return currentNode;
            }
            currentNode = currentNode.next();
        }
        if(currentNode.getActivity().getTime().equals(time)){
            return currentNode;
        }
        return null;
    }

}
