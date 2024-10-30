package com.mygdx.game.Util;

import java.util.Objects;

/**
 * An immutable 2-dimensional vector.
 * @param x The x component.
 * @param y The y component.
 */
public record Vec2(int x, int y) {

    /**
     * Creates a new vector between the two given vectors, starting in the first and ending in the second.
     */
    public static Vec2 createBetween(Vec2 first, Vec2 second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        return new Vec2(second.x - first.x, second.y - first.y);
    }

    /**
     * Returns the size of the vector, rounded to the nearest integer.
     */
    public int size() {
        return (int) Math.round(Math.sqrt(x * x + y * y));
    }

    /**
     * Adds the two vectors together and returns the result as a new vector.
     */
    public Vec2 add(Vec2 vector) {
        Objects.requireNonNull(vector);
        return new Vec2(x + vector.x, y + vector.y);
    }

    /**
     * Scales this vector with the given factor and returns the result as a new vector.
     */
    public Vec2 scale(double factor) {
        int scaledX = (int) Math.round(x * factor);
        int scaledY = (int) Math.round(y * factor);
        return new Vec2(scaledX, scaledY);
    }

    /**
     * Scales this vector to the given length and returns the result as a new vector.
     * @param length The length to scale to. Cannot be 0.
     */
    public Vec2 scaleToSize(int length) {
        if (x == 0 && y == 0) {
            throw new IllegalStateException("Vector size is 0. Scaling a 0 vector is nonsense.");
        }
        float factor = (float) length / (float) this.size();
        return scale(factor);
    }

    /**
     * Calculates the distance between the two vectors and returns it.
     */
    public static int distanceBetween(Vec2 first, Vec2 second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        int x_diff = first.x - second.x;
        int y_diff = first.y - second.y;
        return (int) Math.sqrt(x_diff * x_diff + y_diff * y_diff);
    }
}
