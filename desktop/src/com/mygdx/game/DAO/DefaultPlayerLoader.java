package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.game_object.renderer.*;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.Stats;
import com.mygdx.game.util.Vec2;
import com.mygdx.game.game_object.Transform;
import com.mygdx.game.inventory.Inventory;
import com.mygdx.game.inventory.Item;
import com.mygdx.game.inventory.Tool;
import com.mygdx.game.inventory.ToolType;

import java.util.HashMap;
import java.util.Map;

import static com.mygdx.game.game_object.renderer.Direction.DOWN;

public final class DefaultPlayerLoader {


    private static final String path = "player/player.json";

    public static Player load(){
        JsonReader reader = new JsonReader();
        JsonValue file = reader.parse(Gdx.files.internal(path));

        JsonValue positionJson = file.get("position");
        int x = positionJson.getInt("x");
        int y = positionJson.getInt("y");
        Vec2 position = new Vec2(x, y);
        Transform transform = new Transform(position);

        String map = file.getString("map");

        String textureAtlas = file.getString("texture_atlas");
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(textureAtlas));

        Renderer renderer = new AnimatedRenderer(loadAnimations(atlas), new Key("idle", DOWN), 0);

        String name = file.getString("name");

        Tool pickaxe = Tool.builder().name("Pickaxe").efficiency(1500).maxDurability(2500).toolType(ToolType.PICKAXE).duration(1250).build();
        Tool axe = Tool.builder().name("Axe").efficiency(2).maxDurability(2500).toolType(ToolType.AXE).duration(1250).build();
        Map<String, Integer> stackSizeMap = new HashMap<>() {{
            put("Pickaxe", 1);
            put("Axe", 1);
        }};

        Stats stats = Stats.builder().health(500).offense(25).defense(25).speed(3).build();

        Inventory inventory = new Inventory(new Item[]{pickaxe, axe, null, null, null, null, null, null, null}, stackSizeMap);
        return new Player(transform, renderer, map, name, inventory, 0, stats);
    }

    private static Map<Key, Animation> loadAnimations(TextureAtlas atlas){
        String[] activities = new String[]{"mine", "walking", "idle"};
        int[] lengths = new int[]{4, 6, 1};
        String[] directions = new String[]{"RIGHT", "LEFT", "UP", "DOWN"};
        int lengthIndex = 0;
        Map<Key, Animation> map = new HashMap<>();
        for(String activity : activities){
            for(String direction : directions){
                Frame[] frames = new Frame[lengths[lengthIndex]];
                for(int i = 1; i <= lengths[lengthIndex]; i++){
                    Frame frame = Frame.builder().texture(atlas.findRegion(activity + "_" + direction.toLowerCase(), i)).scaleX(2).scaleY(2).build();
                    frames[i - 1] = frame;
                }
                Key key = new Key(activity, Direction.valueOf(direction));
                Animation animation = new Animation(frames, 1250);
                map.put(key, animation);
            }
            lengthIndex++;
        }
        return map;
    }
}
