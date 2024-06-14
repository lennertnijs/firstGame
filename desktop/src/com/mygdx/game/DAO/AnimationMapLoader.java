package com.mygdx.game.DAO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Animation.Animation;
import com.mygdx.game.Animation.AnimationKey;
import com.mygdx.game.Animation.Frame;
import com.mygdx.game.Keys.EntityKey;

import java.util.*;

import static com.mygdx.game.Keys.NPCActivityType.*;
import static com.mygdx.game.Keys.NPCActivityType.WALKING;
import static com.mygdx.game.Util.Direction.*;
import static com.mygdx.game.Util.Direction.UP;

public class AnimationMapLoader {


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

        Animation miningRightAnimation = loadAnimation(atlas, "mine_right", 4);
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
