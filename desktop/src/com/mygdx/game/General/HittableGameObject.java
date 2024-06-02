package com.mygdx.game.General;

import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;

public class HittableGameObject extends GameObject{

    private AnimationRepository animationRepository;

    public HittableGameObject(Point position, Dimensions dimensions, String map) {
        super(position, dimensions, map);
    }
}
