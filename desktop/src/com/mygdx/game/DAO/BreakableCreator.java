package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Breakables.Breakable;
import com.mygdx.game.Loot.Loot;
import com.mygdx.game.Loot.LootTable;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BreakableCreator {

    public static List<Breakable> getBreakables(){
        TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal("images/stone.png")));
        LootTable lootTable = new LootTable(Collections.singletonList(new Loot("stone", 15)), Collections.singletonList(1.0));

        Breakable stone1 = Breakable.builder().textureRegion(texture).position(new Point(50, 50))
                .dimensions(new Dimensions(60, 40)).map("main").health(50).hardness(25)
                .lootTable(lootTable).type("Stone").build();

        Breakable stone2 = Breakable.builder().textureRegion(texture).position(new Point(350, 350))
                .dimensions(new Dimensions(60, 40)).map("main").health(50).hardness(25)
                .lootTable(lootTable).type("Stone").build();
        return new ArrayList<>(Arrays.asList(stone1, stone2));
    }
}
