package com.mygdx.game.Rat;

import com.mygdx.game.Bat.MonsterData;
import com.mygdx.game.Util.MovementUtil;
import com.mygdx.game.Util.Point;

public final class RatAttackState implements RatState{

    public RatAttackState(){

    }

    @Override
    public Point move(MonsterData data, Rat rat){
        int movement = (int) (data.delta() * data.movementSpeed());
        return MovementUtil.calculateNextPosition(data.monsterPosition(), data.playerPosition(), movement);
    }
}
