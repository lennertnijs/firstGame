package com.mygdx.game.Bat;

import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.GameObject.Monster;
import com.mygdx.game.Keys.ActivityType;
import com.mygdx.game.Loot.LootTable;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

import java.util.List;

public final class Bat extends Monster{

    private final int aggressionRange;
    private final int attackRange;
    private BatState batState;
    public Bat(Point position, Dimensions dimensions, String map,
               AnimationRepository animationRepository, double delta,
               Direction direction, List<ActivityType> activityTypes,
               Stats stats, LootTable lootTable) {
        super(position, dimensions, map, animationRepository, delta, direction, activityTypes, lootTable, stats);
        aggressionRange = 1200;
        attackRange = 600;
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
