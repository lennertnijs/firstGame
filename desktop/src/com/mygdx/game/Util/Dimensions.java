package com.mygdx.game.Util;

/**
 * Represents dimensions in a 2D space.
 */
public final class Dimensions {

    private final int width;
    private final int height;

    /**
     * Creates a new immutable {@link Dimensions}.
     * @param width The width. Cannot be negative.
     * @param height The height. Cannot be negative.
     */
    public Dimensions(int width, int height){
        if(width < 0)
            throw new IllegalArgumentException("Width is negative.");
        if(height < 0)
            throw new IllegalArgumentException("Height is negative.");
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
        int result = width;
        result = result * 31 + height;
        return result;
    }

    @Override
    public String toString(){
        return String.format("Dimensions[width=%d, height=%d]", width, height);
    }
}
