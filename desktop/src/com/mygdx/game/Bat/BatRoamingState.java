package com.mygdx.game.Bat;

import com.mygdx.game.Util.Point;
import com.mygdx.game.UtilMethods.RandomNumberGenerator;

public class BatRoamingState implements BatState{

    private final Bat bat;
    private double delta = 0;
    private final static double interval = 5000;
    private Point goal;

    public BatRoamingState(Bat bat){
        this.bat = bat;
    }

    public void move(Point playerPosition, double delta){
        this.delta += delta;
        if(delta < interval){
            return;
        }
        this.delta = 0;
        // set goal
    }

    private void setGoal(MonsterData data){
        while(goal == null){
            int random_x = RandomNumberGenerator.generateBetween(-200, 200);
            int random_y = RandomNumberGenerator.generateBetween(-200, 200);
            this.goal = new Point(data.monsterPosition().x() + random_x, data.monsterPosition().y() + random_y);
        }
    }
}
