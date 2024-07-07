package com.mygdx.game;

import com.mygdx.game.HitBox.HitBox;
import com.mygdx.game.Util.Point;

import java.util.List;

public final class HitBoxSnapShot {

    private final List<HitBox> hitBoxes;

    public HitBoxSnapShot(List<HitBox> hitBoxes){
        this.hitBoxes = hitBoxes;
    }

    public boolean isFree(HitBox h){
        for(HitBox hitBox : hitBoxes){
            if(hitBox.overlaps(h))
                return false;
        }
        return true;
    }
}
