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
     * @param position The position. Cannot be null.
     * @param dimensions The dimensions of object in the world. Cannot be null.
     * @param map The map. Cannot be null.
     * @param health The health. Cannot be negative or 0.
     * @param hardness The hardness. Cannot be negative or 0.
     * @param type The type. Cannot be null.
     * @param lootTable The loot table. Cannot be null.
     */
    public Breakable(TextureRegion t, Point position, Dimensions dimensions, String map,
                     int health, int hardness, String type, ILootTable lootTable){
        super(t, position, dimensions, map);
        if(health <= 0){
            throw new IllegalArgumentException("Health is negative or 0.");
        }
        if(hardness <= 0){
            throw new IllegalArgumentException("Hardness is negative or 0.");
        }
        this.health = health;
        this.hardness = hardness;
        this.lootTable = Objects.requireNonNull(lootTable, "Loot table is null.");
        this.type = Objects.requireNonNull(type, "Type is null.");
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
}
