package com.mygdx.game.Loot;

public final class Bucket {

    private Loot loot1;
    private double probability1;
    private Loot loot2;
    private double probability2;

    public Bucket(Loot loot1, double prob1, Loot loot2, double prob2){
        this.loot1 = loot1;
        this.probability1 = prob1;
        this.loot2 = loot2;
        this.probability2 = prob2;
    }

    public double getProbability1(){
        return probability1;
    }

    public Loot getLoot1(){
        return loot1;
    }

    public void setLoot2(Loot loot){
        this.loot2 = loot;
    }

    public void setProbability2(double d){
        this.probability2 = d;
    }

    public double getSumOfProbabilities(){
        return probability1 + probability2;
    }

    public Loot getRandom(){
        int d = (int) Math.floor(Math.random() * (probability2 + probability1));
        if(d <= probability1){
            return loot1;
        }
        return loot2;

    }

}
