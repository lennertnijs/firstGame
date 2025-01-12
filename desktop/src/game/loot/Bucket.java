package game.loot;

import java.util.Objects;

public final class Bucket<T> {

    private T firstOption;
    private double firstProbability;
    private T secondOption;
    private double secondProbability;

    public Bucket(T firstOption, double firstProbability){
        this.firstOption = Objects.requireNonNull(firstOption, "First option is null.");
        if(firstProbability <= 0 || firstProbability > 1){
            throw new IllegalArgumentException("p <= 0 || p > 1");
        }
        this.firstProbability = firstProbability;
        this.secondOption = null;
        this.secondProbability = 0;
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
        if(probability <= 0 || probability > 1){
            throw new IllegalArgumentException("p <= 0 || p > 1");
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
        if(probability <= 0 || probability > 1){
            throw new IllegalArgumentException("p <= 0 || p > 1");
        }
        this.secondProbability = probability;
    }

    public T getRandomOption(){
        double random = Math.random() * (firstProbability + secondProbability);
        if(random < firstProbability){
            return firstOption;
        }
        return secondOption;
    }
}
