package com.mygdx.game.Animation;

import com.mygdx.game.Util.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnimationHolder {

    private final Map<String, AnimationPack> animationpacks;

    public AnimationHolder(){
        this.animationpacks = new HashMap<>();
    }

    public void addAnimation(String activity, AnimationPack animationPack){
        animationpacks.put(activity, animationPack);
    }

    public Animation getAnimation(String activity, Direction direction){
        return animationpacks.get(activity).get(direction);
    }
}
