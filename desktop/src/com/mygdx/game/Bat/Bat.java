package com.mygdx.game.Bat;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.GameObject.Monster;
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
               Direction direction,
               Stats stats, LootTable lootTable) {
        super(position, dimensions, map, animations, delta, direction, lootTable, stats);
        batState = new BatRoamingState(this);
    }

    public void update(double delta, Point playerPosition){
        MonsterData monsterData = new MonsterData(playerPosition, getPosition(), getSpeed(),delta);
        // move
        // update activity Type
    }

    public void setBatState(BatState batState){
        this.batState = batState;
    }

    @Override
    public TextureRegion getTexture() {
        return null;
    }

    @Override
    public Point getPosition() {
        return null;
    }
}
