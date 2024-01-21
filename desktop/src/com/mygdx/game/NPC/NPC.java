package com.mygdx.game.NPC;

import com.badlogic.gdx.Gdx;

public class NPC {

    private final String name;
    private int x;
    private int y;
    private WeekSchedule weekSchedule;
    private String png;
    private int goalX;
    private int goalY;

    public NPC(Builder builder){
        this.name = builder.name;
        this.x = builder.x;
        this.y = builder.y;
        this.weekSchedule = builder.weekSchedule;
        this.png = builder.png;
        this.goalX = builder.goalX;
        this.goalY = builder.goalY;
        }

    public void update(){
        this.move();
    }

    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public WeekSchedule getWeekSchedule(){
        return weekSchedule;
    }

    public String getPng(){
        return png;
    }

    public int getGoalX(){
        return goalX;
    }

    public int getGoalY(){
        return goalY;
    }

    public String getName(){
        return name;
    }

    public boolean moveUp(){
        return y < goalY;
    }

    public boolean moveRight(){
        return x < goalX;
    }

    public static Builder builder(){
        return new NPC.Builder();
    }

    public void move(){
        if(goalX != x || goalY == y ){
            x = x - 70*Gdx.graphics.getDeltaTime() >= goalX ? (int)(x - 2000*Gdx.graphics.getDeltaTime()) : goalX;
            y = y - 70*Gdx.graphics.getDeltaTime() >= goalY ? (int) (y - 2000*Gdx.graphics.getDeltaTime()) : goalY;
        }
    }

    public static class Builder{
        private String name;
        private int x;
        private int y;
        private WeekSchedule weekSchedule;
        private String png;
        private int goalX;
        private int goalY;

        private Builder(){

        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder x(int x){
            this.x = x;
            return this;
        }

        public Builder y(int y){
            this.y = y;
            return this;
        }

        public Builder weekSchedule(WeekSchedule weekSchedule){
            this.weekSchedule = weekSchedule;
            return this;
        }

        public Builder png(String png){
            this.png = png;
            return this;
        }

        public Builder goalX(int goalX){
            this.goalX = goalX;
            return this;
        }

        public Builder goalY(int goalY){
            this.goalY = goalY;
            return this;
        }

        public NPC build(){
            return new NPC(this);
        }
    }
}
