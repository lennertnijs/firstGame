package com.mygdx.game.Bat;

import com.mygdx.game.Util.Point;

public interface BatState {

    Point move(MonsterData monsterData, Bat bat);
}
