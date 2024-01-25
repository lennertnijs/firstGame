package com.mygdx.game.Entity;

public class Position2D {

    private int x;
    private int y;

    public Position2D(int x, int y){
        if(x < 0 || y < 0){
            throw new IllegalArgumentException("2D points cannot have negative coordinates");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int newX){
        if(newX < 0){
            throw new IllegalArgumentException("2D points cannot have negative coordinates");
        }
        this.x = newX;
    }

    public void setY(int newY){
        if(newY < 0){
            throw new IllegalArgumentException("2D points cannot have negative coordinates");
        }
        this.y = newY;
    }

    public boolean equals(Position2D position2D){
        return this.x == position2D.x && this.y == position2D.y;
    }
}
