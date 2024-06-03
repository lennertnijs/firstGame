package com.mygdx.game.Bat;

import com.mygdx.game.Util.Point;

public class BatRoamingState implements BatState{

    private double delta = 0;
    private final static double interval = 5000;

    public BatRoamingState(){
    }

    public Point move(MonsterData data, Bat bat){
        delta += data.delta();
        if(delta < interval){
            return data.monsterPosition();
        }
        int movement = (int)(data.movementSpeed() * data.delta());
        int random_x = generateRandom(movement);
        int random_y = generateRandom(movement);
        bat.setBatState(new BatAttackState());
        //todo if within range, set to attack or reposition
        return new Point(data.monsterPosition().x() + random_x, data.monsterPosition().y() + random_y);
    }

    //todo move this out
    private int generateRandom(int x){
        int random = (int)(Math.random() * x);
        double randSign = Math.random();
        if(randSign < 0.5){
            return random;
        }else{
            return -random;
        }
    }
}
