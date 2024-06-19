package com.mygdx.game.Bat;

import com.mygdx.game.UtilMethods.UtilMethods;
import com.mygdx.game.Util.Point;
import com.mygdx.game.UtilMethods.RandomNumberGenerator;

public class BatRoamingState implements BatState{

    private double delta = 0;
    private final static double interval = 5000;
    private Point goal;

    public BatRoamingState(){
    }

    public Point move(MonsterData data, Bat bat){
        delta += data.delta();
        if(delta < interval){
            return data.monsterPosition();
        }
        delta = 0;
        setGoal(data);
        int movement = (int)(data.movementSpeed() * data.delta());
        int distance = 200;
        if(distance <= 1200){
            bat.setBatState(new BatAttackState());
            return data.monsterPosition();
        }
        if(distance <= 600){
            bat.setBatState(new BatRepositionState());
            return data.monsterPosition();
        }
        return UtilMethods.calculateNextPosition(data.monsterPosition(), goal, movement);
    }

    private void setGoal(MonsterData data){
        while(goal == null){
            int random_x = RandomNumberGenerator.generateBetween(-200, 200);
            int random_y = RandomNumberGenerator.generateBetween(-200, 200);
            this.goal = new Point(data.monsterPosition().getX() + random_x, data.monsterPosition().getY() + random_y);
        }
    }
}
