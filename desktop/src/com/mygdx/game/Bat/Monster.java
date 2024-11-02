package com.mygdx.game.Bat;

import com.mygdx.game.game_object.GameObject;
import com.mygdx.game.Loot.LootTable;
import com.mygdx.game.renderer.Renderer;
import com.mygdx.game.Player.Stats;
import com.mygdx.game.game_object.Transform;

public class Monster extends GameObject {

    private final LootTable lootTable;
    private final Stats stats;
    private MonsterState monsterState;


    public Monster(Transform transform, Renderer renderer, String map, LootTable lootTable, Stats stats) {
        super(transform, renderer, map);
        this.lootTable = lootTable;
        this.stats = stats;
    }

    public int getSpeed(){
        return stats.getSpeed();
    }

    public int getAggressionRange(){
        return 750;
    }

    public void setState(MonsterState state){
        this.monsterState = state;
    }
}
