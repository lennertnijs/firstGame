package com.mygdx.game.Util;

import java.util.Objects;

public record Vector(int x, int y) {

    public static Vector between(Point start, Point end) {
        Objects.requireNonNull(start, "Start is null.");
        Objects.requireNonNull(end, "End is null.");
        int x_diff = end.x() - start.x();
        int y_diff = end.y() - start.y();
        return new Vector(x_diff, y_diff);
    }

    /**
     * Rounds down/up to the nearest integer.
     */
    public int size() {
        return (int) Math.round(Math.sqrt(x * x + y * y));
    }


    /**
     * Rounds down/up to the nearest integer.
     */
    public Vector scale(double factor) {
        int scaledX = (int) Math.round(x * factor);
        int scaledY = (int) Math.round(y * factor);
        return new Vector(scaledX, scaledY);
    }

    public Vector scaleToSize(int length) {
        if (this.size() == 0)
            throw new IllegalStateException("Vector size is 0. Scaling a 0 vector is nonsense.");
        float factor = (float) length / (float) this.size();
        return scale(factor);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Vector vector))
            return false;
        return x == vector.x && y == vector.y;
    }

    @Override
    public String toString() {
        return String.format("Vector[x=%d, y=%d]", x, y);
    }
}
