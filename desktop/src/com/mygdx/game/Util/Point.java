package com.mygdx.game.Util;

import java.util.Objects;

/**
 * A 2-dimensional point (x, y)
 *
 * @param x The x coordinate.
 * @param y The y coordinate.
 */
public record Point(int x, int y) {

    /**
     * Creates a new IMMUTABLE {@link Point} with the given coordinates.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Point {
    }
    /**
     * Adds the given vector to this point and returns the result as a new point.
     *
     * @param vector The vector. Cannot be null.
     * @return The newly created point.
     */
    public Point add(Vector vector) {
        Objects.requireNonNull(vector, "Vector is null.");
        return new Point(x + vector.x(), y + vector.y());
    }

    public static int distanceBetween(Point a, Point b) {
        int x_diff = a.x - b.x;
        int y_diff = a.y - b.y;
        return (int) Math.sqrt(x_diff * x_diff + y_diff * y_diff);
    }

    /**
     * Compares two objects and returns true if they're equal points. Returns false otherwise.
     *
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Point point))
            return false;
        return x == point.x && y == point.y;
    }

    /**
     * @return The string representation.
     */
    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}