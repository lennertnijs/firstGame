package com.mygdx.game.V2.TextureSelector;

public interface IAnimationRepository<T> {

    IAnimation<T> getAnimation(AnimationKey key);
}
