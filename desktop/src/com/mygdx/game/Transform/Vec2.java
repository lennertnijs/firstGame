package com.mygdx.game.Transform;

import java.util.Objects;

public final class Vec2 {

    private final int x;
    private final int y;

    public Vec2(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }

    public Vec2 add(Vec2 vector){
        Objects.requireNonNull(vector);
        return new Vec2(x + vector.x, y + vector.y);
    }
}
