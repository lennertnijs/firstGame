package com.mygdx.game.Bat;

import com.mygdx.game.GameObject.Monster;
import com.mygdx.game.Loot.LootTable;
import com.mygdx.game.Renderer.Renderer;
import com.mygdx.game.Stats;
import com.mygdx.game.Util.Point;

import java.util.Objects;

public final class Bat extends Monster{

    private BatState state;

    public Bat(Builder builder) {
        super(builder.renderer, builder.position, builder.map, builder.lootTable, builder.stats);
        this.state = new BatIdleState(this);
    }

    public void update(double delta, Point playerPosition){
        state.handle(delta, playerPosition);
        super.updateDelta(delta);
    }

    public int aggressionRange(){
        return 750;
    }

    public void setState(BatState newState){
        this.state = Objects.requireNonNull(newState, "New bat state is null.");
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private Renderer renderer;
        private Point position = null;
        private String map = null;
        private LootTable lootTable = null;
        private Stats stats = null;

        private Builder(){
        }

        public Builder renderer(Renderer renderer){
            this.renderer = renderer;
            return this;
        }

        public Builder position(Point position){
            this.position = position;
            return this;
        }

        public Builder map(String map){
            this.map = map;
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
