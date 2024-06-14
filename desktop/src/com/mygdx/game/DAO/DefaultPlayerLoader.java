package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.Animation.Frame;
import com.mygdx.game.GameObject.Player;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Inventory.Item;
import com.mygdx.game.Inventory.Tool;
import com.mygdx.game.Inventory.ToolType;
import com.mygdx.game.Keys.ActivityType;
import com.mygdx.game.Keys.EntityKey;
import com.mygdx.game.Keys.NPCActivityType;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;
import com.mygdx.game.Util.Vector;

import java.util.*;

import static com.mygdx.game.Keys.NPCActivityType.*;
import static com.mygdx.game.Keys.NPCActivityType.WALKING;
import static com.mygdx.game.Util.Direction.*;
import static com.mygdx.game.Util.Direction.UP;

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
        Map<AnimationKey, Animation> animationMap = load(textureAtlas);

        Direction direction = Direction.valueOf(file.getString("direction"));

        ActivityType activityType = NPCActivityType.valueOf(file.getString("activity_type"));

        String name = file.getString("name");

        Tool pickaxe = new Tool("Pickaxe", 2000, 20000, 2500, ToolType.PICKAXE);
        Tool axe = new Tool("Axe", 2000, 20000, 2500, ToolType.AXE);
        Inventory inventory = new Inventory(new Item[]{pickaxe, axe});
        return Player.builder()
                .position(position)
                .dimensions(dimensions)
                .map(map)
                .animationMap(animationMap)
                .direction(direction)
                .activityType(activityType)
                .name(name)
                .inventory(inventory)
                .build();
    }

    public static Map<AnimationKey, Animation> load(String atlasPath){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(atlasPath));
        Map<AnimationKey, Animation> map = new HashMap<>();

        Frame idleUpFrame = Frame.builder().textureRegion(atlas.findRegion("idle_up")).build();
        Frame idleRightFrame = Frame.builder().textureRegion(atlas.findRegion("idle_right")).build();
        Frame idleDownFrame = Frame.builder().textureRegion(atlas.findRegion("idle_down")).build();
        Frame idleLeftFrame = Frame.builder().textureRegion(atlas.findRegion("idle_left")).build();

        Animation idleUpAnimation = new Animation(Collections.singletonList(idleUpFrame), 1);
        Animation idleRightAnimation = new Animation(Collections.singletonList(idleRightFrame), 1);
        Animation idleDownAnimation = new Animation(Collections.singletonList(idleDownFrame), 1);
        Animation idleLeftAnimation = new Animation(Collections.singletonList(idleLeftFrame), 1);

        map.put(new EntityKey(IDLING, UP), idleUpAnimation);
        map.put(new EntityKey(IDLING, RIGHT), idleRightAnimation);
        map.put(new EntityKey(IDLING, DOWN), idleDownAnimation);
        map.put(new EntityKey(IDLING, LEFT), idleLeftAnimation);

        Frame mineRight1 = Frame.builder().textureRegion(atlas.findRegion("mine_right", 1)).translation(new Vector(-12, 0)).build();
        Frame mineRight2 = Frame.builder().textureRegion(atlas.findRegion("mine_right", 2)).translation(new Vector(5, 0)).build();
        Frame mineRight3 = Frame.builder().textureRegion(atlas.findRegion("mine_right", 3)).translation(new Vector(5, 0)).build();
        Frame mineRight4 = Frame.builder().textureRegion(atlas.findRegion("mine_right", 4)).translation(new Vector(-4, 0)).build();

        Animation miningRightAnimation = new Animation(Arrays.asList(mineRight1, mineRight2, mineRight3, mineRight4), 1000f);
        Animation miningLeftAnimation = loadAnimation(atlas, "mine_left", 4);
        Animation miningUpAnimation = loadAnimation(atlas, "mine_up", 4);
        Animation miningDownAnimation = loadAnimation(atlas, "mine_down", 4);

        map.put(new EntityKey(MINING, RIGHT), miningRightAnimation);
        map.put(new EntityKey(MINING, DOWN), miningDownAnimation);
        map.put(new EntityKey(MINING, LEFT), miningLeftAnimation);
        map.put(new EntityKey(MINING, UP), miningUpAnimation);

        Animation walkingRightAnimation = loadAnimation(atlas, "walking_right", 6);
        Animation walkingLeftAnimation = loadAnimation(atlas, "walking_left", 6);
        Animation walkingUpAnimation = loadAnimation(atlas, "walking_up", 6);
        Animation walkingDownAnimation = loadAnimation(atlas,"walking_down", 6);

        map.put(new EntityKey(WALKING, RIGHT), walkingRightAnimation);
        map.put(new EntityKey(WALKING, DOWN), walkingDownAnimation);
        map.put(new EntityKey(WALKING, LEFT), walkingLeftAnimation);
        map.put(new EntityKey(WALKING, UP), walkingUpAnimation);

        return map;
    }

    private static Animation loadAnimation(TextureAtlas atlas, String name, int amountOfFrames){
        List<Frame> frames = new ArrayList<>();
        for(int i = 1; i <= amountOfFrames; i++){
            Frame frame = Frame.builder().textureRegion(atlas.findRegion(name, i)).build();
            frames.add(frame);
        }
        return new Animation(frames, 1000f);
    }
}
