package com.mygdx.game.Rat;

import com.mygdx.game.Bat.MonsterData;
import com.mygdx.game.Util.Point;

public interface RatState {

    Point move(MonsterData data, Rat rat);
}
