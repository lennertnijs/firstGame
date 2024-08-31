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

    private Animation createWithDirection(String activity, String direction, int amountOfFrames){
        List<Frame> framesRight = new ArrayList<>(amountOfFrames);
        for(int i = 1; i <= amountOfFrames; i++){
            String name_right = activity + "_" + direction;
            TextureRegion texture_right = atlas.findRegion(name_right, i); // one based indices
            Frame frame = Frame.builder().textureRegion(texture_right).build();
            framesRight.add(frame);
        }
        return new Animation(framesRight, 2);
    }

}
