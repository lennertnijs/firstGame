package com.mygdx.game.Graph;

public class Vertex {
    private final String name;
    private final int x;
    private final int y;

    /**
     * Basic vertex constructor
     *
     * @param name name of the vertex location
     * @param x x coordinate
     * @param y y coordinate
     */
    public Vertex(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * @return the vertex's x coordinate
     */
    public int getX(){
        return x;
    }

    /**
     * @return the vertex's y coordinate
     */
    public int getY(){
        return y;
    }

    /**
     * @return the vertex's location name
     */
    public String getName(){
        return name;
    }
}
