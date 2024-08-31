package com.mygdx.game.GameObject;

import com.mygdx.game.Animation.AnimationHolder;
import com.mygdx.game.Loot.LootTable;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

public abstract class Monster extends Entity{

    private final LootTable lootTable;
    private final Stats stats;


    public Monster(Point position, Dimensions dimensions, String map,
                   AnimationHolder animationHolder,
                   double delta, Direction direction,
                   LootTable lootTable, Stats stats) {
        super(position, dimensions, map, animationHolder, delta, direction);
        this.lootTable = lootTable;
        this.stats = stats;
    }

    public int getSpeed(){
        return stats.getSpeed();
    }
}
