package com.mygdx.game.Bat;

import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;

public final class BatRepositionState implements BatState{

    private Point goal;

    public BatRepositionState(){
    }

    @Override
    public Point move(MonsterData data, Bat bat) {
        if(goal == null){
            goal = data.monsterPosition().add(new Vector(data.playerPosition().x() - data.monsterPosition().x(),
                    data.playerPosition().y() - data.monsterPosition().y()).scale(1.2f));
        }
        return goal; // return correct shizzle
    }
}
