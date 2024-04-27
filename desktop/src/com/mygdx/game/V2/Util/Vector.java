package com.mygdx.game.V2.Util;

/**
 * Represents a 2 dimensional vector.
 */
public final class Vector {

    /**
     * The x component of this {@link Vector}.
     */
    private final int x;
    /**
     * The y component of this {@link Vector}.
     */
    private final int y;

    /**
     * Creates a new immutable {@link Vector}.
     * @param x The x component.
     * @param y The y component.
     */
    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return The x component.
     */
    public int x(){
        return x;
    }

    /**
     * @return The y component.
     */
    public int y(){
        return y;
    }

    /**
     * Scales this {@link Vector}'s components with the given factor and returns the new scaled {@link Vector}.
     * @param factor The factor.
     *
     * @return The new & scaled immutable {@link Vector}.
     */
    public Vector scale(double factor){
        int scaledX = (int)Math.round(x * factor);
        int scaledY = (int)Math.round(y * factor);
        return new Vector(scaledX, scaledY);
    }

    /**
     * Scales this {@link Vector} to the given length and returns the newly sized {@link Vector}.
     * @param length The length.
     *
     * @return The new & resized immutable {@link Vector}.
     */
    public Vector scaleToSize(int length){
        int factor = length / this.size();
        return scale(factor);
    }

    /**
     * @return The size of this {@link Vector}.
     */
    public int size(){
        return (int)Math.round(Math.sqrt(x * x + y * y));
    }

    /**
     * Compares this {@link Vector} to the given object and returns true if they're equal. Returns false otherwise.
     * Two {@link Vector}s are equal if they have the same x and y component.
     *
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Vector))
            return false;
        Vector vector = (Vector) other;
        return x == vector.x && y == vector.y;
    }

    /**
     * @return The hash code of this {@link Vector}.
     */
    @Override
    public int hashCode(){
        int result = x;
        result = result * 31 + y;
        return result;
    }

    /**
     * @return The string representation of this {@link Vector}.
     */
    @Override
    public String toString(){
        return String.format("Vector[x=%d, y=%d]", x, y);
    }
}
