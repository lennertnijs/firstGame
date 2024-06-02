package com.mygdx.game;

import com.mygdx.game.GameObject.GameObject;
import com.mygdx.game.GameObject.NPC;
import com.mygdx.game.GameObject.Player;

import java.util.ArrayList;
import java.util.List;

public class GameObjectRepository {

    private final Player player;
    private final List<NPC> npcs;
    private final List<GameObject> miscObjects;

    public GameObjectRepository(List<NPC> npcs, Player player){
        this.npcs = npcs;
        this.player = player;
        this.miscObjects = new ArrayList<>();
    }

    public List<NPC> getNpcs(){
        return npcs;
    }

    public Player getPlayer(){
        return player;
    }

    public List<GameObject> getMiscObjects(){
        return miscObjects;
    }
}
