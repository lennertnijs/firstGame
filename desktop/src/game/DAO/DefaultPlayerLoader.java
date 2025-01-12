package game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import game.game_object.renderer.*;
import game.inventory.Inventory;
import game.inventory.Item;
import game.inventory.ItemType;
import game.player.Player;
import game.stats.Stats;
import game.util.Direction;
import game.util.Vec2;
import game.game_object.Transform;

import java.util.HashMap;
import java.util.Map;

import static game.util.Direction.DOWN;

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

        Item pickaxe = Item.builder().itemType(ItemType.PICKAXE).amount(1).durability(2500).maxDurability(2500).damage(5).build();

        Stats stats = Stats.builder().health(500).offense(25).defense(25).speed(3).build();

        Inventory inventory = new Inventory(new Item[]{pickaxe, null, null, null, null, null, null, null, null});
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
                activity = activity.equals("walking") ? "walk" : activity;
                Key key = new Key(activity, Direction.valueOf(direction));
                Animation animation = new Animation(frames, 1250);
                map.put(key, animation);
            }
            lengthIndex++;
        }
        return map;
    }
}
