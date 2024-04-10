package com.mygdx.game.V2.TextureSelector;

public interface IAnimationRepository<T> {

    Animation<T> getAnimation(AnimationKey key);
}
