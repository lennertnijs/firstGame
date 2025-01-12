package game.monster;

import game.game_object.GameObject;
import game.game_object.Transform;
import game.loot.LootTable;
import game.game_object.renderer.Renderer;

import java.util.Objects;

public final class Monster extends GameObject {

    private final LootTable lootTable;
    private final MonsterStats stats;
    private MonsterState monsterState;


    public Monster(Transform transform, Renderer renderer, String map, LootTable lootTable, MonsterStats stats) {
        super(transform, renderer, map, null);
        this.lootTable = Objects.requireNonNull(lootTable);
        this.stats = Objects.requireNonNull(stats);
    }

    public MonsterStats getStats(){
        return stats;
    }

    public void changeState(MonsterState state){
        this.monsterState = Objects.requireNonNull(state);
    }
}
