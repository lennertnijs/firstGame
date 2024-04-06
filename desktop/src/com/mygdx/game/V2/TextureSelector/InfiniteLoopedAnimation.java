package com.mygdx.game.V2.TextureSelector;

import java.util.Objects;

public final class InfiniteLoopedAnimation<T> implements IAnimation<T>{

    private final Animation<T> animation;

    public InfiniteLoopedAnimation(Animation<T> animation){
        Objects.requireNonNull(animation, "Cannot create a LoopedAnimation from null.");
        this.animation = animation;
    }


    @Override
    public T get(float delta){
        if(delta < 0)
            throw new IllegalArgumentException("Cannot have a negative delta.");
        float deltaWithinRange = delta % animation.getDuration();
        return animation.getFrame(deltaWithinRange);
    }
}
