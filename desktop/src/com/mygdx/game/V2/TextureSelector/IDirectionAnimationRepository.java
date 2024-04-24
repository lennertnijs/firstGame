package com.mygdx.game.V2.TextureSelector;

import com.mygdx.game.V2.Util.Direction;

public interface IDirectionAnimationRepository<T> {

    OldAnimation<T> getAnimation(Direction direction);
}
