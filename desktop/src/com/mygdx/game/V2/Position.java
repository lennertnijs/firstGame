package com.mygdx.game.V2;

public class Position {

    private final int x;
    private final int y;

    private Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static Position create(int x, int y){
        Validator.notNegative(x);
        Validator.notNegative(y);
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
        Position p = (Position) other;
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode(){
        int result = x;
        result = result * 31 + y;
        return result;
    }

    @Override
    public String toString(){
        return String.format("Position[x = %d, y = %d]", x, y);
    }
}
