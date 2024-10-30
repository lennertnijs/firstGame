package com.mygdx.game.Breakables;

import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.Loot.ILootTable;
import com.mygdx.game.Loot.Loot;
import com.mygdx.game.GameObject.Renderer;
import com.mygdx.game.GameObject.Transform;

import java.util.Objects;

/**
 * Represents breakable objects in the game.
 * Examples are rocks, trees, and other various gemstones.
 */
public final class Breakable extends GameObject{

    /**
     * The current health
     */
    private int health;
    /**
     * The hardness.
     */
    private final int hardness;
    /**
     * The loot table.
     */
    private final ILootTable lootTable;
    /**
     * The type of breakable.
     */
    private final String type;

    /**
     * Creates a new, damageable {@link Breakable} object.
     * Breakable objects can be damaged and, when fully broken,
     */
    public Breakable(Builder builder){
        super(builder.transform, builder.renderer, builder.map);
        this.health = builder.health;
        this.hardness = builder.hardness;
        this.lootTable = builder.lootTable;
        this.type = builder.type;
    }

    /**
     * @return The health.
     */
    public int getHealth(){
        return health;
    }

    /**
     * @return The hardness.
     */
    public int hardness(){
        return hardness;
    }

    /**
     * @return The breakable type.
     */
    public String type(){
        return type;
    }

    /**
     * @return The loot dropped if the breakable is broken.
     *
     * @throws IllegalStateException If this method is called while the breakable is not broken.
     */
    public Loot getDrops(){
        return lootTable.getRandomLoot();
    }

    /**
     * @return True if the breakable is broken. False otherwise.
     */
    public boolean isBroken(){
        return health == 0;
    }

    /**
     * Damages this breakable with the given amount.
     * @param damage The damage. Cannot be negative.
     */
    public void damage(int damage){
        if(damage < 0){
            throw new IllegalArgumentException("Damage is negative.");
        }
        this.health = Math.max(health - damage, 0);
    }

    /**
     * @return The string representation.
     */
    @Override
    public String toString(){
        return String.format("Breakable[%s, health=%d, hardness=%d, lootTable=%s, type=%s]",
                super.toString(), health, hardness, lootTable, type);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private Transform transform;
        private Renderer renderer;
        private String map = null;
        private int health = -1;
        private int hardness = -1;
        private ILootTable lootTable = null;
        private String type = null;

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

        public Builder health(int health){
            this.health = health;
            return this;
        }

        public Builder hardness(int hardness){
            this.hardness = hardness;
            return this;
        }

        public Builder lootTable(ILootTable lootTable){
            this.lootTable = lootTable;
            return this;
        }

        public Builder type(String type){
            this.type = type;
            return this;
        }

        public Breakable build(){
            Objects.requireNonNull(map, "Map is null.");
            if(health <= 0){
                throw new IllegalArgumentException("Health is negative or zero.");
            }
            if(hardness < 0){
                throw new IllegalArgumentException("Hardness is negative.");
            }
            Objects.requireNonNull(lootTable, "Loot table is null.");
            Objects.requireNonNull(type, "Type is null.");
            return new Breakable(this);
        }
    }
}
