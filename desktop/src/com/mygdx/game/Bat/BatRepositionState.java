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
            Vector vector = Vector.createBetweenPoints(data.playerPosition(), data.monsterPosition()).scale(1.2f);
            goal = data.monsterPosition().add(vector);
        }
        return goal; // return correct shizzle
    }
}
