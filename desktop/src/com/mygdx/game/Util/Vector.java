package com.mygdx.game.Util;

public final class Vector {

    private final int x;
    private final int y;

    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static Vector createBetweenPoints(Point start, Point end){
        int x_diff = end.getX() - start.getX();
        int y_diff = end.getY() - start.getY();
        return new Vector(x_diff, y_diff);
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }

    /**
     * Rounds down/up to the nearest integer.
     */
    public int size(){
        return (int)Math.round(Math.sqrt(x * x + y * y));
    }


    /**
     * Rounds down/up to the nearest integer.
     */
    public Vector scale(double factor){
        int scaledX = (int)Math.round(x * factor);
        int scaledY = (int)Math.round(y * factor);
        return new Vector(scaledX, scaledY);
    }

    public Vector scaleToSize(int length){
        if(this.size() == 0)
            throw new IllegalStateException("Vector size is 0. Scaling a 0 vector is nonsense.");
        float factor = (float)length / (float)this.size();
        return scale(factor);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Vector))
            return false;
        Vector vector = (Vector) other;
        return x == vector.x && y == vector.y;
    }

    @Override
    public int hashCode(){
        return x * 31 + y;
    }

    @Override
    public String toString(){
        return String.format("Vector[x=%d, y=%d]", x, y);
    }
}
