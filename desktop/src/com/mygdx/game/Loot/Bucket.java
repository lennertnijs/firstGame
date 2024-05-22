package com.mygdx.game.Loot;

import java.util.Objects;

public final class Bucket<T> {

    private T firstOption;
    private double firstProbability;
    private T secondOption;
    private double secondProbability;

    public Bucket(T firstOption, double firstProbability, T secondOption, double secondProbability){
        this.firstOption = Objects.requireNonNull(firstOption, "First option is null.");
        if(firstProbability < 0 || firstProbability > 1){
            throw new IllegalArgumentException("First probability is negative or above 1.");
        }
        this.firstProbability = firstProbability;
        this.secondOption = Objects.requireNonNull(secondOption, "Second option is null.");
        if(secondProbability < 0 || secondProbability > 1){
            throw new IllegalArgumentException("Second probability is negative or above 1.");
        }
        this.secondProbability = secondProbability;
    }

    public T getFirstOption(){
        return firstOption;
    }

    public void setFirstOption(T option){
        this.firstOption = Objects.requireNonNull(option, "Option is null.");
    }

    public double getFirstProbability(){
        return firstProbability;
    }

    public void setFirstProbability(double probability){
        if(probability < 0 || probability > 1){
            throw new IllegalArgumentException("First probability is negative or above 1.");
        }
        this.firstProbability = probability;
    }

    public T getSecondOption(){
        return secondOption;
    }

    public void setSecondOption(T option){
        this.secondOption = Objects.requireNonNull(option, "Option is null.");
    }

    public double getSecondProbability(){
        return secondProbability;
    }

    public void setSecondProbability(double probability){
        if(probability < 0 || probability > 1){
            throw new IllegalArgumentException("Second probability is negative or above 1.");
        }
        this.secondProbability = probability;
    }

    public double getSummedProbabilities(){
        return firstProbability + secondProbability;
    }


    public T getRandomOption(){
        double random = Math.random() * (firstProbability + secondProbability);
        if(random <= firstProbability){
            return firstOption;
        }
        return secondOption;
    }
}
