package com.mygdx.game.Util;

public record Dimensions(int width, int height) {

    public Dimensions {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Dimension is negative.");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Dimensions dimensions))
            return false;
        return width == dimensions.width && height == dimensions.height;
    }

    @Override
    public String toString() {
        return String.format("Dimensions[width=%d, height=%d]", width, height);
    }
}
