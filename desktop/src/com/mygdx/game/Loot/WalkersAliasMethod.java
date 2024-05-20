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
            buckets.add(new Bucket(loots.get(i), probabilities.get(i), null, 0));
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
            if(bucket.getSumOfProbabilities() == average){
                full.add(bucket);
            }
            if(bucket.getSumOfProbabilities() > average){
                overFull.add(bucket);
            }
            if(bucket.getSumOfProbabilities() < average){
                underFull.add(bucket);
            }
        }
        while(!underFull.isEmpty()){
            Bucket b1 = underFull.get(0);
            Bucket b2 = overFull.get(0);
            double prob1 = b1.getProbability1();
            double prob2 = b2.getProbability1();
            double toAdd = average - prob1;
            b1.setProbability2(toAdd);
            b1.setLoot2(b2.getLoot1());
            b2.setProbability2(prob2 - toAdd);
            full.add(b1);
            underFull.remove(b1);
            if(b2.getSumOfProbabilities() == average){
                overFull.remove(b2);
                full.add(b2);
            }
        }
        return full;
    }
}
