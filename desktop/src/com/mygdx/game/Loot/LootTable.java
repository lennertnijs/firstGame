package com.mygdx.game.Loot;

import java.util.List;

public final class LootTable implements ILootTable{

    private final static WalkersAlias<Loot> walkersAlias = new WalkersAlias<>();
    private final List<Bucket<Loot>> buckets;

    public LootTable(List<Loot> loots, List<Double> probabilities){
        this.buckets = walkersAlias.generateBuckets(loots, probabilities);
    }

    public Loot getRandomLoot(){
        int randomBucketIndex = (int) (Math.random() * buckets.size());
        return buckets.get(randomBucketIndex).getRandomOption();
    }
}
