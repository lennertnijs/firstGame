package game.Breakables;

import game.game_object.GameObject;
import game.game_object.GameObjectType;
import game.loot.ILootTable;
import game.loot.Loot;
import game.game_object.renderer.Renderer;
import game.game_object.Transform;

import java.util.Objects;

public final class Breakable extends GameObject {

    private int health;
    private final int hardness;
    private final ILootTable lootTable;

    public Breakable(Builder builder){
        super(builder.transform, builder.renderer, builder.map, builder.type);
        this.health = builder.health;
        this.hardness = builder.hardness;
        this.lootTable = builder.lootTable;
    }

    public int getHealth(){
        return health;
    }

    public int hardness(){
        return hardness;
    }

    public Loot getDrops(){
        return lootTable.getRandomLoot();
    }

    public boolean isBroken(){
        return health == 0;
    }

    public void damage(int damage){
        if(damage < 0){
            throw new IllegalArgumentException("Damage is negative.");
        }
        this.health = Math.max(health - damage, 0);
        System.out.println(health);
    }

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
        private GameObjectType type = null;

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

        public Builder type(GameObjectType type){
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
            // Objects.requireNonNull(lootTable, "loot table is null.");
            Objects.requireNonNull(type, "Type is null.");
            return new Breakable(this);
        }
    }
}
