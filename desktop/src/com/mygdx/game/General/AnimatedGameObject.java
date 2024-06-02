package com.mygdx.game.General;

import com.mygdx.game.AnimationRepository.AnimationKey;
import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.AnimationRepository.Frame;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;

import java.util.Objects;

public class AnimatedGameObject extends GameObject{

    private final AnimationRepository animationRepository;
    private double delta;
    public AnimatedGameObject(Point position, Dimensions dimensions, String map,
                              AnimationRepository animationRepository, double delta) {
        super(position, dimensions, map);
        this.animationRepository = Objects.requireNonNull(animationRepository, "Animation repository is null.");
        this.delta = delta;
    }

    public Frame getFrame(AnimationKey key){
        Objects.requireNonNull(key, "Key is null.");
        return animationRepository.get(key).getFrame(delta);
    }


}
