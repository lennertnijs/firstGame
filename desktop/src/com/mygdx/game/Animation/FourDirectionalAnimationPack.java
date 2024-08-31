package com.mygdx.game.Animation;

import com.mygdx.game.Util.Direction;

import java.util.HashMap;
import java.util.Map;

public final class FourDirectionalAnimationPack implements AnimationPack{

    private final Map<Direction, Animation> map;

    public FourDirectionalAnimationPack(Animation right, Animation down, Animation left, Animation up){
        this.map = new HashMap<>(4);
        map.put(Direction.RIGHT, right);
        map.put(Direction.DOWN, down);
        map.put(Direction.LEFT, left);
        map.put(Direction.UP, up);
    }

    public Animation get(Direction direction){
        return map.get(direction);
    }
}
