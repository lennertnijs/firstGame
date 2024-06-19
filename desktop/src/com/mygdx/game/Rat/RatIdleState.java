package com.mygdx.game.Rat;

import com.mygdx.game.Bat.MonsterData;
import com.mygdx.game.UtilMethods.UtilMethods;
import com.mygdx.game.Util.Point;
import com.mygdx.game.UtilMethods.RandomNumberGenerator;

public final class RatIdleState implements RatState{

    private double delta = 0;
    private final static int interval = 5000;
    private Point goal;

    public RatIdleState(){

    }


    @Override
    public void move(MonsterData data, Rat rat) {
        this.delta = data.delta();
        // nothing
    }

    private void setGoal(MonsterData data){
        while(goal == null){
            int random_x = RandomNumberGenerator.generateBetween(-200, 200);
            int random_y = RandomNumberGenerator.generateBetween(-200, 200);
            this.goal = new Point(data.monsterPosition().x() + random_x, data.monsterPosition().y() + random_y);
        }
    }

}
