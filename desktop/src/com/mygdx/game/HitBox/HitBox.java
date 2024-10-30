package com.mygdx.game.HitBox;

import com.mygdx.game.Util.Vec2;

public interface HitBox{

    boolean contains(Vec2 point);
    boolean overlaps(HitBox hitBox);
}
