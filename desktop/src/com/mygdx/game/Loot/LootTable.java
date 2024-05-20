package com.mygdx.game.Loot;

import java.util.List;

public final class LootTable {

    private final static WalkersAliasMethod walkers = new WalkersAliasMethod();
    private final List<Bucket> buckets;

    public LootTable(List<Loot> loots, List<Double> probabilities){
        this.buckets = walkers.walkersAlias(loots, probabilities);
    }

    public Loot getRandomLoot(){
        int d = (int) Math.floor(Math.random() * buckets.size());
        Bucket randomBucket = buckets.get(d);
        return randomBucket.getLoot1();
    }
}
