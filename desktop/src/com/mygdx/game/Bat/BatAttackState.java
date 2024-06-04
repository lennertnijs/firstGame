package com.mygdx.game.Bat;

import com.mygdx.game.Util.MovementUtil;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;

public final class BatAttackState implements BatState{

    public Point goal;

    public BatAttackState(){
    }

    @Override
    public Point move(MonsterData data, Bat bat){
        if(goal == null){
            setGoal(data, bat);
        }
        int movement = (int) (data.movementSpeed() * data.delta());
        return MovementUtil.calculateNextPosition(data.monsterPosition(), goal, movement);
    }

    private void setGoal(MonsterData data, Bat bat){
        while(goal == null){
            float factor = 1.25f;
            Vector vector = new Vector(data.playerPosition().x() - bat.getPosition().x(),
                    data.playerPosition().y() - bat.getPosition().y()).scale(factor);
            goal = new Point(vector.x() + bat.getPosition().x(), vector.y() + bat.getPosition().y());
            // check within boundaries
            factor *= 0.95f;
        }
    }
}