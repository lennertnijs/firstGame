package com.mygdx.game.Rat;

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

public final class Rat extends Monster {

    private final static int aggressionRange = 1200;
    private final static int attackRange = 600;
    private RatState ratState;

    public Rat(Point position, Dimensions dimensions, String map,
               Map<AnimationKey, Animation> animations, double delta,
               Direction direction,
               LootTable lootTable, Stats stats) {
        super(position, dimensions, map, animations, delta, direction, lootTable, stats);
    }

    public void setRatState(RatState ratState){
        this.ratState = ratState;
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
