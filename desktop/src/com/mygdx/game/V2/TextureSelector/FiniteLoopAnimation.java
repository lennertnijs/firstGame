package com.mygdx.game.V2.TextureSelector;

import java.util.Objects;

public final class FiniteLoopAnimation<T> implements LoopAnimation<T> {


    private final OldAnimation<T> oldAnimation;
    private final int maxLoops;
    private final OldAnimation<T> fallBackOldAnimation;

    public FiniteLoopAnimation(OldAnimation<T> oldAnimation, int maxLoops, OldAnimation<T> fallBackOldAnimation){
        Objects.requireNonNull(oldAnimation, "Cannot create a FiniteLoopAnimation with null Animation.");
        if(maxLoops < 0)
            throw new IllegalArgumentException("Cannot have a negative amount of loops.");
        Objects.requireNonNull(fallBackOldAnimation, "Cannot create a FiniteLoopAnimation with null fallback Animation.");
        this.oldAnimation = oldAnimation;
        this.maxLoops = maxLoops;
        this.fallBackOldAnimation = fallBackOldAnimation;
    }


    @Override
    public T getFrame(float delta) {
        if(delta < 0)
            throw new IllegalArgumentException("Cannot have a negative delta.");
        float deltaWithinRange = delta % oldAnimation.getDuration();
        boolean finishedLoops = delta / oldAnimation.getDuration() >= maxLoops;
        if(finishedLoops)
            return fallBackOldAnimation.getFrame(deltaWithinRange);
        return oldAnimation.getFrame(deltaWithinRange);
    }
}
