package com.mygdx.game.GameObject;

import com.mygdx.game.Animation.AnimationHolder;
import com.mygdx.game.Animation.Frame;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

public class AnimatedGameObject extends GameObject{

    private final AnimationHolder animationHolder;
    private double animationDelta;
    public AnimatedGameObject(Point position, Dimensions dimensions, String map,
                              AnimationHolder animationHolder, double animationDelta) {
        super(position, dimensions, map);
        this.animationHolder = animationHolder;
        this.animationDelta = animationDelta;
    }

    public Frame getFrame(String activity, Direction direction){
        return animationHolder.getAnimation("activity", Direction.RIGHT).getFrame(animationDelta);
    }

    public void increaseAnimationDelta(double increase){
        animationDelta += increase;
    }

    public void resetAnimationDelta(){
        this.animationDelta = 0;
    }
}
