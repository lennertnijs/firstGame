package com.mygdx.game.V2.TextureSelector;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

public final class LoopedAnimation implements IAnimation{

    private final Animation<Texture> animation;

    private LoopedAnimation(Animation<Texture> animation){
        this.animation = animation;
    }

    public static LoopedAnimation create(Animation<Texture> animation){
        Objects.requireNonNull(animation, "Cannot create a LoopedAnimation from null.");
        return new LoopedAnimation(animation);
    }

    @Override
    public Texture getTexture(float delta){
        if(delta < 0)
            throw new IllegalArgumentException("Cannot get a Texture from a LoopedAnimation because the delta is negative.");
        float deltaWithinRange = delta % animation.getDuration();
        return animation.getFrame(deltaWithinRange);
    }
}
