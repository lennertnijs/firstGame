package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.AnimationRepository.AnimationRepository;
import com.mygdx.game.GameObject.Player;
import com.mygdx.game.Inventory.Inventory;
import com.mygdx.game.Keys.ActivityType;
import com.mygdx.game.Keys.NPCActivityType;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Point;

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
        AnimationRepository animationRepository = new AnimationRepository(AnimationMapLoader.load(textureAtlas));

        Direction direction = Direction.valueOf(file.getString("direction"));

        ActivityType activityType = NPCActivityType.valueOf(file.getString("activity_type"));

        String name = file.getString("name");


        Inventory inventory = new Inventory(9);
        return Player.builder()
                .position(position)
                .dimensions(dimensions)
                .map(map)
                .animationRepository(animationRepository)
                .direction(direction)
                .activityType(activityType)
                .name(name)
                .inventory(inventory)
                .build();
    }
}
