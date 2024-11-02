package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.renderer.Renderer;
import com.mygdx.game.renderer.StaticRenderer;
import com.mygdx.game.inventory.Inventory;
import com.mygdx.game.inventory.Item;
import com.mygdx.game.inventory.Tool;
import com.mygdx.game.inventory.ToolType;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.UpdatedUtil.Vec2;
import com.mygdx.game.renderer.Frame;

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
        Vec2 position = new Vec2(x, y);

        JsonValue dimensionsJson = file.get("dimensions");
        int width = dimensionsJson.getInt("width");
        int height = dimensionsJson.getInt("height");

        String map = file.getString("map");

        String textureAtlas = file.getString("texture_atlas");
        //AnimationHolder animationHolder = load("player/Player.pack");
        TextureRegion tr = new TextureRegion(new Texture(Gdx.files.internal("player/Player.png")));
        Frame frame = Frame.builder().texture(tr).build();
        Renderer renderer = new StaticRenderer(frame);
        Direction direction = Direction.valueOf(file.getString("direction"));

        String name = file.getString("name");

        Tool pickaxe = Tool.builder().name("Pickaxe").efficiency(1500).maxDurability(2500).toolType(ToolType.PICKAXE).build();
        Tool axe = Tool.builder().name("Axe").efficiency(2).maxDurability(2500).toolType(ToolType.AXE).build();

        Map<String, Integer> stackSizeMap = new HashMap<>() {{
            put("Pickaxe", 1);
            put("Axe", 1);
        }};

        Inventory inventory = new Inventory(new Item[]{pickaxe, axe}, stackSizeMap);
        return Player.builder().renderer(renderer).map(map).name(name).inventory(inventory).build();
    }
}
