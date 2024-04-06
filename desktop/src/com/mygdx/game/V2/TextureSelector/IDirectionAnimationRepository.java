package com.mygdx.game.V2.TextureSelector;

import com.mygdx.game.V2.Direction;

public interface IDirectionAnimationRepository<T> {

    Animation<T> getAnimation(Direction direction);
}
