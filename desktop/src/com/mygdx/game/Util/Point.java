package com.mygdx.game.Util;

import java.util.Objects;

/**
 * A 2-dimensional point (x, y)
 */
public final class Point {

    /**
     * The x coordinate.
     */
    private int x;
    /**
     * The y coordinate.
     */
    private int y;

    /**
     * Creates a new MUTABLE {@link Point} with the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return The x coordinate.
     */
    public int getX(){
        return x;
    }

    /**
     * Sets the x coordinate to the given value.
     * @param x The x coordinate.
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * @return The y coordinate.
     */
    public int getY(){
        return y;
    }

    /**
     * Sets the y coordinate to the given value.
     * @param y The y coordinate.
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Adds the given vector to this point and returns the result as a new point.
     * DOES NOT CHANGE the original point.
     * @param vector The vector. Cannot be null.
     *
     * @return The newly created point.
     */
    public Point add(Vector vector){
        Objects.requireNonNull(vector, "Vector is null.");
        return new Point(x + vector.x(), y + vector.y());
    }

    /**
     * Compares two objects and returns true if they're equal points. Returns false otherwise.
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
     * @return The hash code.
     */
    @Override
    public int hashCode(){
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    /**
     * @return The string representation.
     */
    @Override
    public String toString(){
        return String.format("(%d, %d)", x, y);
    }
}