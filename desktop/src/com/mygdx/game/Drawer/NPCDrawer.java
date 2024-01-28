package com.mygdx.game.Drawer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGame;
import com.mygdx.game.NPC.NPC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static com.mygdx.game.Constants.NPC_HEIGHT;
import static com.mygdx.game.Constants.NPC_WIDTH;

public class NPCDrawer {

    final HashMap<String, Texture> npcTextures = new HashMap<>();
    final MyGame game;
    public NPCDrawer(MyGame game){
        this.game = game;
    }

    public void loadNPCTextures(ArrayList<NPC> npcs){
        Objects.requireNonNull(npcs, "The list of npcs to load must not be null");
        for(NPC npc: npcs){
            Objects.requireNonNull(npc, "The npc to load must not be null");
            Texture texture = new Texture(Gdx.files.internal(npc.getSpritePath()));
            npcTextures.put(npc.getName(), texture);
        }
    }

    public void drawAllNPCS(ArrayList<NPC> npcs){
        Objects.requireNonNull(npcs, "The list of npcs to load must not be null");
        for(NPC npc: npcs){
            drawNPC(npc);
        }
    }

    private void drawNPC(NPC npc){
        Objects.requireNonNull(npc, "The npc to draw must not be null");
        Texture texture = npcTextures.get(npc.getName());
        game.batch.draw(texture, npc.getPosition().getX(), npc.getPosition().getY(), NPC_WIDTH, NPC_HEIGHT);
    }
}
