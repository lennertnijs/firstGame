package com.mygdx.game.V2.TextureSelector;

import com.mygdx.game.V2.Direction;
import com.mygdx.game.V2.ActivityType;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public class AnimationRepo<T> {

    private final Map<ActivityType, DirectionAnimationRepo<T>> activityMapping;

    public AnimationRepo(Map<ActivityType, DirectionAnimationRepo<T>> activityMapping){
        validateMap(activityMapping);
        this.activityMapping = activityMapping;
    }

    private void validateMap(Map<ActivityType, DirectionAnimationRepo<T>> map){
        Objects.requireNonNull(map, "Cannot make a DirectionAnimationRepo from null.");
        if(map.containsKey(null) || map.containsValue(null))
            throw new NullPointerException("Cannot make a DirectionAnimationRepo with a null value.");
    }

    public Map<ActivityType, DirectionAnimationRepo<T>> getMapping(){
        return activityMapping;
    }

    public Animation<T> getAnimation(ActivityType type, Direction direction){
        Objects.requireNonNull(type, "Cannot get an Animation for a null ActivityType.");
        if(!activityMapping.containsKey(type))
            throw new NoSuchElementException("No mapping for the given ActivityType was found.");
        Objects.requireNonNull(direction, "Cannot get an Animation for a null Direction.");
        return activityMapping.get(type).getAnimation(direction);
    }
}
