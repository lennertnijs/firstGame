package com.mygdx.game.GameObject;

import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.Animation.Frame;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public class AnimatedGameObject extends GameObject{

    private final Map<AnimationKey, Animation> map;
    private double animationDelta;
    public AnimatedGameObject(Point position, Dimensions dimensions, String map,
                              Map<AnimationKey, Animation> animationMap, double animationDelta) {
        super(position, dimensions, map);
        Objects.requireNonNull(animationMap, "Map is null.");
        if(animationMap.containsKey(null) || animationMap.containsValue(null)) {
            throw new NullPointerException("Map contains a null key.");
        }
        this.map = new HashMap<>(animationMap);
        this.animationDelta = animationDelta;
    }

    public Frame getFrame(AnimationKey key){
        Objects.requireNonNull(key, "Key is null.");
        if(!map.containsKey(key)) {
            throw new NoSuchElementException("No mapping found.");
        }
        return map.get(key).getFrame(animationDelta);
    }

    public void increaseAnimationDelta(double increase){
        animationDelta += increase;
    }

    public void resetAnimationDelta(){
        this.animationDelta = 0;
    }
}
