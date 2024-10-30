package com.mygdx.game.Bat;

import com.mygdx.game.Loot.LootTable;
import com.mygdx.game.GameObject.Renderer;
import com.mygdx.game.Stats;
import com.mygdx.game.GameObject.Transform;
import com.mygdx.game.Util.Vec2;

import java.util.Objects;

public final class Bat extends Monster{

    private BatState state;

    public Bat(Builder builder) {
        super(builder.transform, builder.renderer, builder.map, builder.lootTable, builder.stats);
        this.state = new BatIdleState(this);
    }

    public void update(double delta, Vec2 playerPosition){
        state.handle(delta, playerPosition);
        renderer.update(delta);
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

        private Transform transform;
        private Renderer renderer;
        private String map = null;
        private LootTable lootTable = null;
        private Stats stats = null;

        private Builder(){
        }

        public Builder transform(Transform transform){
            this.transform = transform;
            return this;
        }

        public Builder renderer(Renderer renderer){
            this.renderer = renderer;
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
