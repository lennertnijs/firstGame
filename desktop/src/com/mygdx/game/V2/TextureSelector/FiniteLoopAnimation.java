package com.mygdx.game.V2.TextureSelector;

import java.util.Objects;

public final class FiniteLoopAnimation<T> implements ILoopedAnimation<T> {


    private final Animation<T> animation;
    private final int maxLoops;
    private final Animation<T> fallBackAnimation;

    public FiniteLoopAnimation(Animation<T> animation, int maxLoops, Animation<T> fallBackAnimation){
        Objects.requireNonNull(animation, "Cannot create a FiniteLoopAnimation with null Animation.");
        if(maxLoops < 0)
            throw new IllegalArgumentException("Cannot have a negative amount of loops.");
        Objects.requireNonNull(fallBackAnimation, "Cannot create a FiniteLoopAnimation with null fallback Animation.");
        this.animation = animation;
        this.maxLoops = maxLoops;
        this.fallBackAnimation = fallBackAnimation;
    }


    @Override
    public T getFrame(float delta) {
        if(delta < 0)
            throw new IllegalArgumentException("Cannot have a negative delta.");
        float deltaWithinRange = delta % animation.getDuration();
        boolean finishedLoops = delta / animation.getDuration() > maxLoops;
        if(finishedLoops)
            return fallBackAnimation.getFrame(deltaWithinRange);
        return animation.getFrame(deltaWithinRange);
    }
}
