package com.mygdx.game.Bat;

import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.GameObject.Monster;
import com.mygdx.game.Keys.ActivityType;
import com.mygdx.game.Loot.LootTable;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

import java.util.List;
import java.util.Map;

public final class Bat extends Monster{

    private final static int aggressionRange = 1200;
    private final static int attackRange = 600;
    private BatState batState;
    public Bat(Point position, Dimensions dimensions, String map,
               Map<AnimationKey, Animation> animations, double delta,
               Direction direction, List<ActivityType> activityTypes,
               Stats stats, LootTable lootTable) {
        super(position, dimensions, map, animations, delta, direction, activityTypes, lootTable, stats);
        batState = new BatRoamingState();
    }

    public void update(double delta, Point playerPosition){
        MonsterData monsterData = new MonsterData(playerPosition, getPosition(), getSpeed(),delta);
        super.setPosition(batState.move(monsterData, this));
        // update activity Type
    }

    public void setBatState(BatState batState){
        this.batState = batState;
    }
}
