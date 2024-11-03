package com.mygdx.game.monster;

import com.mygdx.game.loot.LootTable;
import com.mygdx.game.game_object.GameObject;
import com.mygdx.game.game_object.Transform;
import com.mygdx.game.game_object.renderer.Renderer;

import java.util.Objects;

public final class Monster extends GameObject {

    private final LootTable lootTable;
    private final MonsterStats stats;
    private MonsterState monsterState;


    public Monster(Transform transform, Renderer renderer, String map, LootTable lootTable, MonsterStats stats) {
        super(transform, renderer, map);
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
