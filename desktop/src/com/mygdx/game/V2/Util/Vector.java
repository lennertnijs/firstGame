package com.mygdx.game.V2.Util;

public final class Vector {

    private final int x;
    private final int y;

    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }

    public Vector scale(float factor){
        return new Vector((int)(x * factor), (int)(y * factor));
    }

    public Vector scaleToSize(int size){
        float factor = (float) (size / size());
        return scale(factor);
    }

    public double size(){
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString(){
        return String.format("Vector[x=%d, y=%d]", x, y);
    }
}
