package com.mygdx.game.effect;

import java.util.Objects;

public final class Effect {

    private final Stat stat;
    private final EffectType type;
    private final double amount;
    private final int duration;
    private int durationLeft;

    public Effect(Stat stat, EffectType type, double amount, int duration){
        this.stat = Objects.requireNonNull(stat);
        this.type = Objects.requireNonNull(type);
        if(amount < 0){
            throw new IllegalArgumentException("Effect amount cannot be negative.");
        }
        this.amount = amount;
        if(duration < 0){
            throw new IllegalArgumentException("Effect duration cannot be negative.");
        }
        this.duration = duration;
        this.durationLeft = duration;
    }

    public Stat stat(){
        return stat;
    }

    public EffectType type(){
        return type;
    }

    public double amount(){
        return amount;
    }

    public int duration(){
        return duration;
    }

    public int getDurationLeft(){
        return durationLeft;
    }

    public boolean isDone(){
        return durationLeft <= 0;
    }

    public void update(double delta){
        this.durationLeft -= delta;
    }
}
