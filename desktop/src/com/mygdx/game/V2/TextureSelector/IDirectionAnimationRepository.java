package com.mygdx.game.V2.TextureSelector;

public interface IDirectionAnimationRepository<T> {

    Animation<T> getAnimation(Direction direction);
}
