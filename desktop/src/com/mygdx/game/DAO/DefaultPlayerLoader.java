package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.Item;
import com.mygdx.game.Inventory.Tool;
import com.mygdx.game.Inventory.ToolType;
import com.mygdx.game.Player.Player;
import com.mygdx.game.GameObject.Renderer;
import com.mygdx.game.GameObject.StaticTexture;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

import java.util.HashMap;
import java.util.Map;

public final class DefaultPlayerLoader {


    private static final String path = "player/player.json";

    public static Player load(){
        JsonReader reader = new JsonReader();
        JsonValue file = reader.parse(Gdx.files.internal(path));

        JsonValue positionJson = file.get("position");
        int x = positionJson.getInt("x");
        int y = positionJson.getInt("y");
        Point position = new Point(x, y);

        JsonValue dimensionsJson = file.get("dimensions");
        int width = dimensionsJson.getInt("width");
        int height = dimensionsJson.getInt("height");
        Dimensions dimensions = new Dimensions(width, height);

        String map = file.getString("map");

        String textureAtlas = file.getString("texture_atlas");
        //AnimationHolder animationHolder = load("player/Player.pack");
        TextureRegion tr = new TextureRegion(new Texture(Gdx.files.internal("player/Player.png")));
        Renderer renderer = new StaticTexture(tr);
        Direction direction = Direction.valueOf(file.getString("direction"));

        String name = file.getString("name");

        Tool pickaxe = Tool.builder().name("Pickaxe").efficiency(1500).maxDurability(2500).toolType(ToolType.PICKAXE).build();
        Tool axe = Tool.builder().name("Axe").efficiency(2).maxDurability(2500).toolType(ToolType.AXE).build();

        Map<String, Integer> stackSizeMap = new HashMap<String, Integer>(){{
            put("Pickaxe", 1);
            put("Axe", 1);
        }};

        Inventory inventory = Inventory.createWithItems(new Item[]{pickaxe, axe}, stackSizeMap);
        return Player.builder()
                .renderer(renderer)
                .position(position)
                .map(map)
                .name(name)
                .inventory(inventory)
                .build();
    }
}
