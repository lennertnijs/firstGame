package com.mygdx.game.Rat;

import com.mygdx.game.Bat.MonsterData;
import com.mygdx.game.UtilMethods.MovementUtil;
import com.mygdx.game.Util.Point;
import com.mygdx.game.UtilMethods.RandomNumberGenerator;

public final class RatIdleState implements RatState{

    private double delta = 0;
    private final static int interval = 5000;
    private Point goal;

    public RatIdleState(){

    }


    @Override
    public Point move(MonsterData data, Rat rat) {
        this.delta = data.delta();
        if(delta <= interval){
            return data.monsterPosition();
        }
        delta = 0;
        setGoal(data);
        int movement = (int)(data.movementSpeed() * data.delta());
        int distance = 200;
        if(distance <= 1200){
            rat.setRatState(null);
            return data.monsterPosition();
        }
        if(distance <= 600){
            rat.setRatState(null);
            return data.monsterPosition();
        }
        return MovementUtil.calculateNextPosition(data.monsterPosition(), goal, movement);
    }

    private void setGoal(MonsterData data){
        while(goal == null){
            int random_x = RandomNumberGenerator.generateBetween(-200, 200);
            int random_y = RandomNumberGenerator.generateBetween(-200, 200);
            this.goal = new Point(data.monsterPosition().x() + random_x, data.monsterPosition().y() + random_y);
        }
    }

}
