package com.mygdx.game.Util;

import java.util.Objects;

/**
 * A 2-dimensional point (x, y)
 */
public final class Point {

    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public Point add(Vector vector){
        Objects.requireNonNull(vector, "Vector is null.");
        return new Point(x + vector.x(), y + vector.y());
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Point))
            return false;
        Point point = (Point) other;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString(){
        return String.format("(%d, %d)", x, y);
    }
}