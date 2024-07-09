package com.mygdx.game.Bat;

import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.GameObject.Monster;
import com.mygdx.game.Keys.EntityKey;
import com.mygdx.game.Loot.LootTable;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

import java.util.Map;
import java.util.Objects;

public final class Bat extends Monster{

    private final int aggressionRange = 1000;
    private BatState state;
    public Bat(Point position, Dimensions dimensions, String map,
               Map<AnimationKey, Animation> animations, double delta,
               Direction direction,
               Stats stats, LootTable lootTable) {
        super(position, dimensions, map, animations, delta, direction, lootTable, stats);
        state = new BatIdleState(this);
    }

    public int aggressionRange(){
        return aggressionRange;
    }

    public void update(double delta, Point playerPosition){
    }

    public void setState(BatState newState){
        this.state = Objects.requireNonNull(newState, "New bat state is null.");
    }

    @Override
    public EntityKey generateEntityKey() {
        return null;
    }
}
