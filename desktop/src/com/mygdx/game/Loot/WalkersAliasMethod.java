package com.mygdx.game.Loot;

import java.util.ArrayList;
import java.util.List;

public final class WalkersAliasMethod {

    public WalkersAliasMethod(){
    }


    public List<Bucket> createBuckets(List<Loot> loots, List<Double> probabilities){
        double average = 1d / probabilities.size();
        List<Bucket> buckets = new ArrayList<>();
        for(int i = 0; i < loots.size(); i++){
            buckets.add(new Bucket(loots.get(i), probabilities.get(i)));
        }
        return buckets;
    }

    public List<Bucket> walkersAlias(List<Loot> loots, List<Double> probabilities){
        List<Bucket> buckets = createBuckets(loots, probabilities);
        List<Bucket> underFull = new ArrayList<>();
        List<Bucket> overFull = new ArrayList<>();
        List<Bucket> full = new ArrayList<>();
        double average = 1d / probabilities.size();
        for(Bucket bucket : buckets){
            double summed = bucket.getFirstProbability() + bucket.getSecondProbability();
            if(summed == average){
                full.add(bucket);
            }
            if(summed > average){
                overFull.add(bucket);
            }
            if(summed < average){
                underFull.add(bucket);
            }
        }
        while(!underFull.isEmpty()){
            Bucket b1 = underFull.get(0);
            Bucket b2 = overFull.get(0);
            double prob1 = b1.getFirstProbability();
            double prob2 = b2.getFirstProbability();
            double toAdd = average - prob1;
            b1.setSecondProbability(toAdd);
            b1.setSecondOption(b2.getFirstOption());
            b2.setSecondProbability(prob2 - toAdd);
            full.add(b1);
            underFull.remove(b1);
            if(b2.getFirstProbability() + b2.getSecondProbability() == average){
                overFull.remove(b2);
                full.add(b2);
            }
        }
        return full;
    }
}
