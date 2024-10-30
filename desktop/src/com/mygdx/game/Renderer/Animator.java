package com.mygdx.game.Renderer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.AnimationPack;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Vector;

import java.util.Map;

public final class Animator implements Renderer {

    private final Map<String, AnimationPack> animationPacks;
    private double delta;
    private String activity;
    private Direction direction;

    public Animator(Map<String, AnimationPack> animationPacks){
        this.animationPacks = animationPacks;
        this.delta = 0;
        this.activity = "idle";
        this.direction = Direction.RIGHT;
    }

    @Override
    public TextureRegion texture() {
        return animationPacks.get(activity).get(direction).getFrame(delta).textureRegion();
    }

    @Override
    public Vector getOffSet() {
        return animationPacks.get(activity).get(direction).getFrame(delta).textureTranslation();
    }

    @Override
    public Dimensions getDimensions() {
        return animationPacks.get(activity).get(direction).getFrame(delta).dimensions();
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public void update(double delta) {
        this.delta += delta;
    }
}
