package com.mygdx.game.Breakables;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.Loot.ILootTable;
import com.mygdx.game.Loot.Loot;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;

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
        super(builder.textureRegion, builder.position, builder.dimensions, builder.map);
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

        private TextureRegion textureRegion = null;
        private Point position = null;
        private Dimensions dimensions = null;
        private String map = null;
        private int health = -1;
        private int hardness = -1;
        private ILootTable lootTable = null;
        private String type = null;

        private Builder(){

        }

        public Builder textureRegion(TextureRegion textureRegion){
            this.textureRegion = textureRegion;
            return this;
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
            Objects.requireNonNull(textureRegion, "Texture region is null.");
            Objects.requireNonNull(position, "Position is null.");
            if(dimensions == null){
                dimensions = new Dimensions(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
            }
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
