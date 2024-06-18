package com.mygdx.game.GameObject;

import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.Loot.LootTable;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

import java.util.List;
import java.util.Map;

public abstract class Monster extends Entity{

    private final LootTable lootTable;
    private final Stats stats;


    public Monster(Point position, Dimensions dimensions, String map,
                   Map<AnimationKey, Animation> animations,
                   double delta, Direction direction,
                   LootTable lootTable, Stats stats) {
        super(position, dimensions, map, animations, delta, direction);
        this.lootTable = lootTable;
        this.stats = stats;
    }

    public int getSpeed(){
        return stats.getSpeed();
    }
}
