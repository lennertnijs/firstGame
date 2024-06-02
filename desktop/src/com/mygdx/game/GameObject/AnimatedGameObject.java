package com.mygdx.game.GameObject;

import com.mygdx.game.AnimationRepository.AnimationKey;
import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.AnimationRepository.Frame;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;

import java.util.Objects;

public abstract class AnimatedGameObject extends GameObject{

    private final AnimationRepository animationRepository;
    private double animationDelta;

    public AnimatedGameObject(Point position, Dimensions dimensions, String map,
                              AnimationRepository animationRepository, double animationDelta){
        super(null, position, dimensions, map);
        this.animationRepository = Objects.requireNonNull(animationRepository, "Animation repository is null.");
        if(animationDelta < 0){
            throw new IllegalArgumentException("Animation delta is negative.");
        }
        this.animationDelta = animationDelta;
    }

    public Frame getFrame(AnimationKey key){
        Objects.requireNonNull(key, "Key is null.");
        return animationRepository.get(key).getFrame(animationDelta);
    }

    public void increaseAnimationDelta(double increase){
        animationDelta += increase;
    }

    public void resetAnimationDelta(){
        animationDelta = 0;
    }
}
