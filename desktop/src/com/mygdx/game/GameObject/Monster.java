package com.mygdx.game.GameObject;

import com.mygdx.game.Loot.LootTable;
import com.mygdx.game.Renderer.Renderer;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.Point;

public abstract class Monster extends GameObject{

    private final LootTable lootTable;
    private final Stats stats;


    public Monster(Renderer renderer, Point position, String map, LootTable lootTable, Stats stats) {
        super(renderer, position, map);
        this.lootTable = lootTable;
        this.stats = stats;
    }

    public int getSpeed(){
        return stats.getSpeed();
    }
}
