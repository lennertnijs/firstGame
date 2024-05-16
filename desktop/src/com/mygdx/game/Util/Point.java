package com.mygdx.game.Util;

public final class Point {

    private final int x;
    private final int y;

    public Point(int x, int y){
        if(x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinate is negative.");
        }
        this.x = x;
        this.y = y;
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
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
        return x * 31 + y;
    }

    @Override
    public String toString(){
        return String.format("Point[x=%d, y=%d]", x, y);
    }
}