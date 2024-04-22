package com.mygdx.game.V2.Util;

/**
 * Class to represent a 2-dimensional point.
 */
public final class Point {

    /**
     * The x coordinate of this {@link Point}.
     */
    private final int x;
    /**
     * The y coordinate of this {@link Point}.
     */
    private final int y;

    /**
     * Creates a new immutable {@link Point}.
     * @param x The x coordinate. Cannot be negative.
     * @param y The y coordinate. Cannot be negative.
     *
     * @throws IllegalArgumentException If the given x or y coordinate is negative.
     */
    public Point(int x, int y){
        if(x < 0)
            throw new IllegalArgumentException("X is negative.");
        if(y < 0)
            throw new IllegalArgumentException("Y is negative.");
        this.x = x;
        this.y = y;
    }

    /**
     * @return The x coordinate of this {@link Point}.
     */
    public int x(){
        return x;
    }

    /**
     * @return The y coordinate of this {@link Point}.
     */
    public int y(){
        return y;
    }

    /**
     * Compares this {@link Point} to the given object and returns true if they're equal. Returns false otherwise.
     * Two {@link Point}s are equal when they have the same x and y coordinate.
     *
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Point))
            return false;
        Point point = (Point) other;
        return x == point.x && y == point.y;
    }

    /**
     * @return The hash code of this {@link Point}.
     */
    @Override
    public int hashCode(){
        int result = x;
        result = result * 31 + y;
        return result;
    }

    /**
     * @return The string representation of this {@link Point}.
     */
    @Override
    public String toString(){
        return String.format("Point[x=%d, y=%d]", x, y);
    }
}