package com.mygdx.game.Entity;

import java.util.Objects;

public class Position {

    private final int x;
    private final int y;

    public Position(Builder builder){
        this.x = builder.x;
        this.y = builder.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Position)){
            return false;
        }
        Position position = (Position) o;
        return position.x == x && position.y == y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x,y);
    }

    @Override
    public String toString(){
        return "Position(" + this.x + ", " + this.y + ")";
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private int x = -1;
        private int y = -1;

        public Builder(){

        }

        public Builder x(int x){
            this.x = x;
            return this;
        }

        public Builder y(int y){
            this.y = y;
            return this;
        }

        public Position build(){
            if(x < 0 || y < 0){
                throw new IllegalArgumentException("The coordinates of a position must be non-negative");
            }
            return new Position(this);
        }
    }
}
