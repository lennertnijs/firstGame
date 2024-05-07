package com.mygdx.game.Util;

public final class Dimensions {

    private final int width;
    private final int height;

    public Dimensions(int width, int height){
        if(width < 0 || height < 0) {
            throw new IllegalArgumentException("Dimension is negative.");
        }
        this.width = width;
        this.height = height;
    }

    public int width(){
        return width;
    }

    public int height(){
        return height;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Dimensions))
            return false;
        Dimensions dimensions = (Dimensions) other;
        return width == dimensions.width && height == dimensions.height;
    }

    @Override
    public int hashCode(){
        return width * 31 + height;
    }

    @Override
    public String toString(){
        return String.format("Dimensions[width=%d, height=%d]", width, height);
    }
}
