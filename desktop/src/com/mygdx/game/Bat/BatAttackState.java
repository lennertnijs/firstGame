package com.mygdx.game.Bat;

import com.mygdx.game.UtilMethods.UtilMethods;
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
        return UtilMethods.calculateNextPosition(data.monsterPosition(), goal, movement);
    }

    private void setGoal(MonsterData data, Bat bat){
        while(goal == null){
            float factor = 1.25f;
            Vector vector = new Vector(data.playerPosition().getX() - bat.getPosition().getX(),
                    data.playerPosition().getY() - bat.getPosition().getY()).scale(factor);
            goal = new Point(vector.x() + bat.getPosition().getX(), vector.y() + bat.getPosition().getY());
            // check within boundaries
            factor *= 0.95f;
        }
    }
}
