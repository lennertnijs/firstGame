package com.mygdx.game.renderer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.UpdatedUtil.Vec2;
import com.mygdx.game.Util.Direction;

import java.util.Map;
import java.util.Objects;

public final class AnimatedRenderer implements Renderer {

    private final Map<Key, Animation> animations;
    private final Key key;
    private double delta;
    private Frame currentFrame;

    public AnimatedRenderer(Map<Key, Animation> animations, Key key, double delta){
        Objects.requireNonNull(animations);
        for(Key k : animations.keySet()){
            Objects.requireNonNull(k);
        }
        for(Animation animation : animations.values()){
            Objects.requireNonNull(animation);
        }
        this.animations = animations;
        this.key = Objects.requireNonNull(key);
        if(delta < 0){
            throw new IllegalArgumentException("Delta cannot be negative.");
        }
        this.delta = delta;
        if(!animations.containsKey(key)){
            throw new IllegalArgumentException("Key is not mapped.");
        }
        this.currentFrame = animations.get(key).getFrame(delta);
    }

    @Override
    public TextureRegion getTexture() {
        return currentFrame.texture();
    }

    @Override
    public Vec2 getOffSet() {
        return currentFrame.offset();
    }

    @Override
    public int getWidth() {
        return currentFrame.scaledWidth();
    }

    @Override
    public int getHeight(){
        return currentFrame.scaledHeight();
    }

    @Override
    public void setDirection(Direction direction) {
        key.setDirection(direction);
        if(!animations.containsKey(key)){
            throw new IllegalArgumentException("Updated key is not mapped.");
        }
        this.delta = 0;
        this.currentFrame = animations.get(key).getFrame(0);
    }

    @Override
    public void setActivity(String activity) {
        key.setActivity(activity);
        if(!animations.containsKey(key)){
            throw new IllegalArgumentException("Updated key is not mapped.");
        }
        this.delta = 0;
        this.currentFrame = animations.get(key).getFrame(0);
    }

    @Override
    public void update(double delta) {
        this.delta += delta;
        if(!currentFrame.equals(animations.get(key).getFrame(delta))){
            currentFrame = animations.get(key).getFrame(delta);
        }
    }
}
