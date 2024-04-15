package com.mygdx.game.V2.Util;

public final class Position {

    private final int x;
    private final int y;

    private Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static Position create(int x, int y){
        if(x < 0 || y < 0)
            throw new IllegalArgumentException("Cannot create a Position with a negative coordinate value.");
        return new Position(x,y);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Position)){
            return false;
        }
        Position position = (Position) other;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode(){
        int result = x;
        result = result * 31 + y;
        return result;
    }

    @Override
    public String toString(){
        return String.format("Position[x=%d, y=%d]", x, y);
    }
}
