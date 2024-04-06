package com.mygdx.game.V2.TextureSelector;

import com.mygdx.game.V2.ActivityType;
import com.mygdx.game.V2.Direction;

public interface IAnimationRepository<T> {

    Animation<T> getAnimation(ActivityType type, Direction direction);
}
