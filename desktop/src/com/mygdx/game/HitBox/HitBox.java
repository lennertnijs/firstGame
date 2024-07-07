package com.mygdx.game.HitBox;

import com.mygdx.game.Util.Point;

public interface HitBox{

    boolean contains(Point point);
    boolean overlaps(HitBox hitBox);
}
