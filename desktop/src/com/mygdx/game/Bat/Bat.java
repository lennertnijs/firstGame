package com.mygdx.game.Bat;

import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.GameObject.Monster;
import com.mygdx.game.Loot.LootTable;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Bat extends Monster{

    private BatState state;

    public Bat(Builder builder) {
        super(builder.position, builder.dimensions, builder.map, builder.animationMap, builder.delta,
                builder.direction, builder.lootTable, builder.stats);
        this.state = new BatIdleState(this);
    }

    public void update(double delta, Point playerPosition){
        state.handle(delta, playerPosition);
        super.increaseAnimationDelta(delta);
    }

    public int aggressionRange(){
        return 750;
    }

    public void setState(BatState newState){
        this.state = Objects.requireNonNull(newState, "New bat state is null.");
    }

    @Override
    public AnimationKey generateEntityKey() {
        return new BatKey(state.getName());
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private Point position = null;
        private Dimensions dimensions = null;
        private String map = null;
        private Map<AnimationKey, Animation> animationMap = null;
        private double delta = 0;
        private Direction direction = null;
        private LootTable lootTable = null;
        private Stats stats = null;

        private Builder(){
        }

        public Builder position(Point position){
            this.position = position;
            return this;
        }

        public Builder dimensions(Dimensions dimensions){
            this.dimensions = dimensions;
            return this;
        }

        public Builder map(String map){
            this.map = map;
            return this;
        }

        public Builder animationMap(Map<AnimationKey, Animation> map){
            this.animationMap = new HashMap<>(map);
            return this;
        }

        public Builder delta(double delta){
            this.delta = delta;
            return this;
        }

        public Builder direction(Direction direction){
            this.direction = direction;
            return this;
        }

        public Builder lootTable(LootTable lootTable){
            this.lootTable = lootTable;
            return this;
        }

        public Builder stats(Stats stats){
            this.stats = stats;
            return this;
        }

        public Bat build(){
            return new Bat(this);
        }
    }
}
