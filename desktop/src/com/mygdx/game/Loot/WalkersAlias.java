package com.mygdx.game.Loot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class WalkersAlias<T> {

    public WalkersAlias(){
    }

    public List<Bucket<T>> generateBuckets(List<T> objects, List<Double> probabilities){
        validateInputs(objects, probabilities);
        List<Bucket<T>> buckets = createInitialBuckets(objects, probabilities);

        double average = 1d / probabilities.size();
        List<Bucket<T>> underFull = buckets.stream().filter(b -> b.getFirstProbability() < average).collect(Collectors.toList());
        List<Bucket<T>> full = buckets.stream().filter(b -> b.getFirstProbability() == average).collect(Collectors.toList());
        List<Bucket<T>> overFull = buckets.stream().filter(b -> b.getFirstProbability() > average).collect(Collectors.toList());

        while(!underFull.isEmpty()){
            Bucket<T> underFullBucket = underFull.get(0);
            Bucket<T> overFullBucket = overFull.get(0);
            double probabilityToFillUnderFullBucket = average - underFullBucket.getFirstProbability();
            // set the second option in the under full bucket.
            underFullBucket.setSecondOption(overFullBucket.getFirstOption());
            underFullBucket.setSecondProbability(probabilityToFillUnderFullBucket);
            // subtract the amount from the over full bucket. Can lead to all 3 scenarios for this bucket.
            overFullBucket.setFirstProbability(overFullBucket.getFirstProbability() - probabilityToFillUnderFullBucket);

            underFull.remove(0);
            full.add(underFullBucket);
            if(overFullBucket.getFirstProbability() == average){
                overFull.remove(0);
                full.add(overFullBucket);
            }
            if(overFullBucket.getFirstProbability() < average){
                underFull.add(overFullBucket);
                overFull.remove(0);
            }
            if(underFull.size() == 0 && overFull.size() == 1){
                // for floating point like 0.33334, cause the avg would be 0.33333, so its technically overfull
                full.add(overFull.get(0));
                overFull.remove(0);
            }
        }
        return full;
    }

    private void validateInputs(List<T> objects, List<Double> probabilities){
        Objects.requireNonNull(objects, "List is null.");
        if(objects.contains(null)){
            throw new NullPointerException("List contains null.");
        }
        Objects.requireNonNull(probabilities, "List is null.");
        if(probabilities.contains(null)){
            throw new NullPointerException("List contains null.");
        }
        if(objects.size() != probabilities.size()){
            throw new IllegalArgumentException("Lists have different sizes.");
        }
        double summedProb = 0;
        for(Double probability : probabilities){
            summedProb += probability;
        }
        if(summedProb != 1){
            throw new IllegalArgumentException("Summed probability is not 1.");
        }
    }

    private List<Bucket<T>> createInitialBuckets(List<T> objects, List<Double> probabilities){
        List<Bucket<T>> buckets = new ArrayList<>();
        for(int i = 0; i < objects.size(); i++){
            buckets.add(new Bucket<>(objects.get(i), probabilities.get(i)));
        }
        return buckets;
    }
}
