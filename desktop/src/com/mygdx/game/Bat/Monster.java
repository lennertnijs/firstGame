package com.mygdx.game.Bat;

import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.Loot.LootTable;
import com.mygdx.game.GameObject.Renderer;
import com.mygdx.game.Stats;
import com.mygdx.game.GameObject.Transform;

public abstract class Monster extends GameObject {

    private final LootTable lootTable;
    private final Stats stats;


    public Monster(Transform transform, Renderer renderer, String map, LootTable lootTable, Stats stats) {
        super(transform, renderer, map);
        this.lootTable = lootTable;
        this.stats = stats;
    }

    public int getSpeed(){
        return stats.getSpeed();
    }
}
