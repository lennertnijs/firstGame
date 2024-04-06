package com.mygdx.game.V2.TextureSelector;

import com.mygdx.game.V2.Direction;
import com.mygdx.game.V2.ActivityType;

import java.util.Map;

public class AnimationRepo<T> {

    private final Map<ActivityType, DirectionAnimationRepo<T>> texturesPerActivityType;

    private AnimationRepo(Map<ActivityType, DirectionAnimationRepo<T>> texturesPerActivityType){
        this.texturesPerActivityType = texturesPerActivityType;
    }

    public static <T> AnimationRepo<T> create(Map<ActivityType, DirectionAnimationRepo<T>> texturesPerActivityType){
        return new AnimationRepo<>(texturesPerActivityType);
    }

    public Animation<T> getAnimation(ActivityType type, Direction direction){
        return texturesPerActivityType.get(type).getAnimation(direction);
    }
}
