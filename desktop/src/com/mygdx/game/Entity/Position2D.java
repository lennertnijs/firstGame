package com.mygdx.game.Entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Position2D)){
            return false;
        }
        return ((Position2D) o).getX() == x && ((Position2D) o).getY() == y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x,y);
    }
}
