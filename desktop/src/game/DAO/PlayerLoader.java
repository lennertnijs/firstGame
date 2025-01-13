package game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

import static game.util.Direction.*;

public final class PlayerLoader {

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
        Renderer renderer = new AnimatedRenderer(loadAnimations(), new Key("idle", DOWN), 0);
        String name = file.getString("name");
        Item pickaxe = Item.builder().itemType(ItemType.PICKAXE).amount(1).durability(2500).maxDurability(2500).damage(5).build();
        Item axe = Item.builder().itemType(ItemType.AXE).amount(1).durability(2500).maxDurability(2500).damage(5).build();
        Stats stats = Stats.builder().health(500).offense(25).defense(25).speed(3).build();
        Inventory inventory = new Inventory(new Item[]{pickaxe, pickaxe, axe, axe, pickaxe, pickaxe, axe, axe, pickaxe, pickaxe});
        return new Player(transform, renderer, map, name, inventory, 0, stats);
    }

    private static Map<Key, Animation> loadAnimations(){
        Map<Key, Animation> animations = new HashMap<>();
        String[] names = new String[]{"idle", "walk", "mine"};
        Direction[] directions = new Direction[]{UP, RIGHT, DOWN, LEFT};
        for (String name : names) {
            TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal("player/" + name + ".png")));
            Frame frame = Frame.builder().texture(texture).scaleX(2).scaleY(2).build();
            Animation animation = new Animation(new Frame[]{frame}, 100);
            for (Direction direction : directions) {
                Key key = new Key(name, direction);
                animations.put(key, animation);
            }
        }
        return animations;
    }
}
