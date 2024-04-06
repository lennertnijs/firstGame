package com.mygdx.game.V2.TextureSelector;

import com.mygdx.game.V2.Direction;
import com.mygdx.game.V2.NPCActivityType;

import java.util.Map;

public class AnimationRepo<T> {

    private final Map<NPCActivityType, DirectionAnimationRepo<T>> texturesPerActivityType;

    private AnimationRepo(Map<NPCActivityType, DirectionAnimationRepo<T>> texturesPerActivityType){
        this.texturesPerActivityType = texturesPerActivityType;
    }

    public static <T> AnimationRepo<T> create(Map<NPCActivityType, DirectionAnimationRepo<T>> texturesPerActivityType){
        return new AnimationRepo<>(texturesPerActivityType);
    }

    public Animation<T> getAnimation(NPCActivityType type, Direction direction){
        return texturesPerActivityType.get(type).getAnimation(direction);
    }
}
