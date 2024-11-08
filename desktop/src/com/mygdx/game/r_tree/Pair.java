package com.mygdx.game.r_tree;

import java.util.Objects;

public record Pair<T>(T first, T second) {

    public Pair(T first, T second) {
        this.first = Objects.requireNonNull(first);
        this.second = Objects.requireNonNull(second);
    }
}
