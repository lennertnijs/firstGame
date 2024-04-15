package com.mygdx.game.V2.TextureSelector;

import com.mygdx.game.V2.Util.Direction;

public interface IDirectionAnimationRepository<T> {

    Animation<T> getAnimation(Direction direction);
}
