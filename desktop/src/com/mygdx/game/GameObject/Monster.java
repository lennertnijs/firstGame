package com.mygdx.game.GameObject;

import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.Keys.ActivityType;
import com.mygdx.game.Loot.LootTable;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

import java.util.List;

public abstract class Monster extends Entity{

    private final LootTable lootTable;
    private final Stats stats;


    public Monster(Point position, Dimensions dimensions, String map,
                   AnimationRepository animationRepository,
                   double delta, Direction direction, List<ActivityType> activityTypes,
                   LootTable lootTable, Stats stats) {
        super(position, dimensions, map, animationRepository, delta, direction, activityTypes);
        this.lootTable = lootTable;
        this.stats = stats;
    }

    public int getSpeed(){
        return stats.getSpeed();
    }
}
