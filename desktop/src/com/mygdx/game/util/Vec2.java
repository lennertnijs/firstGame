package com.mygdx.game.util;

import java.util.Objects;

/**
 * An immutable 2-dimensional vector.
 */
public record Vec2(int x, int y) {

    public static Vec2 createFromTo(Vec2 first, Vec2 second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        return new Vec2(second.x - first.x, second.y - first.y);
    }

    public int size() {
        return (int) Math.round(Math.sqrt(x * x + y * y));
    }

    public Vec2 add(Vec2 vector) {
        Objects.requireNonNull(vector);
        return new Vec2(x + vector.x, y + vector.y);
    }

    public Vec2 scaleToSize(int length) {
        if (x == 0 && y == 0) {
            return new Vec2(0, 0);
        }
        float factor = (float) length / (float) this.size();
        int scaledX = Math.round(x * factor);
        int scaledY = Math.round(y * factor);
        return new Vec2(scaledX, scaledY);
    }

    public int distanceTo(Vec2 vector){
        Objects.requireNonNull(vector);
        int x_diff = x - vector.x;
        int y_diff = y - vector.y;
        return (int) Math.sqrt(x_diff * x_diff + y_diff * y_diff);
    }

    @Override
    public String toString(){
        return String.format("Vec2[x=%d, y=%d]", x, y);
    }
}
