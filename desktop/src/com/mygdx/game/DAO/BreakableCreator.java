package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.Loot.Loot;
import com.mygdx.game.Loot.LootTable;
import com.mygdx.game.game_object.Renderer;
import com.mygdx.game.game_object.StaticRenderer;
import com.mygdx.game.game_object.Frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BreakableCreator {

    public static List<Breakable> getBreakables(){
        TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal("images/stone.png")));
        LootTable lootTable = new LootTable(Collections.singletonList(new Loot("stone", 15)), Collections.singletonList(1.0));
        Frame frame = Frame.builder().texture(texture).build();
        Renderer renderer = new StaticRenderer(frame);
        Breakable stone1 = Breakable.builder().renderer(renderer).map("main").health(50).hardness(25)
                .lootTable(lootTable).type("Stone").build();

        Breakable stone2 = Breakable.builder().renderer(renderer).map("main").health(50).hardness(25)
                .lootTable(lootTable).type("Stone").build();
        return new ArrayList<>(Arrays.asList(stone1, stone2));
    }
}
