package com.mygdx.game.Util;

import java.util.Objects;

public record Vec2(int x, int y) {

    public Vec2 add(Vec2 vector) {
        Objects.requireNonNull(vector);
        return new Vec2(x + vector.x, y + vector.y);
    }

    public Vec2 scale(double factor) {
        int scaledX = (int) Math.round(x * factor);
        int scaledY = (int) Math.round(y * factor);
        return new Vec2(scaledX, scaledY);
    }

    public int size() {
        return (int) Math.round(Math.sqrt(x * x + y * y));
    }

    public Vec2 scaleToSize(int length) {
        if (this.size() == 0)
            throw new IllegalStateException("Vector size is 0. Scaling a 0 vector is nonsense.");
        float factor = (float) length / (float) this.size();
        return scale(factor);
    }

    public static int distanceBetween(Vec2 a, Vec2 b) {
        int x_diff = a.x - b.x;
        int y_diff = a.y - b.y;
        return (int) Math.sqrt(x_diff * x_diff + y_diff * y_diff);
    }

    public static Vec2 between(Vec2 start, Vec2 end) {
        Objects.requireNonNull(start, "Start is null.");
        Objects.requireNonNull(end, "End is null.");
        int x_diff = end.x() - start.x();
        int y_diff = end.y() - start.y();
        return new Vec2(x_diff, y_diff);
    }
}
