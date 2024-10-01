package com.mygdx.game.Animation;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class AnimationFactory {

    private final TextureAtlas atlas;

    public AnimationFactory(TextureAtlas atlas){
        this.atlas = Objects.requireNonNull(atlas);
    }

    public FourDirectionalAnimationPack create4Directional(String activity, int amountOfFrames){
        Animation animationRight = createWithDirection(activity, "right", amountOfFrames);
        Animation animationDown = createWithDirection(activity, "down", amountOfFrames);
        Animation animationLeft = createWithDirection(activity, "left", amountOfFrames);
        Animation animationUp = createWithDirection(activity, "up", amountOfFrames);
        return new FourDirectionalAnimationPack(animationRight, animationDown, animationLeft, animationUp);
    }

    public Animation createWithDirection(String activity, String direction, int amountOfFrames){
        List<Frame> frames = new ArrayList<>(amountOfFrames);
        for(int i = 1; i <= amountOfFrames; i++){
            String name = activity + "_" + direction;
            TextureRegion texture = atlas.findRegion(name, i); // one based indices
            Frame frame = Frame.builder().textureRegion(texture).build();
            frames.add(frame);
        }
        return new Animation(frames, 2);
    }

}
