package com.mygdx.game.GameObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Vec2;
import com.mygdx.game.Util.Direction;

import java.util.Map;

public final class Animator implements Renderer {

    private final Map<Key, Animation> animations;
    private final Key key;
    private double delta;
    private Frame currentFrame;

    public Animator(Map<Key, Animation> animations, Key key, double delta){
        this.animations = animations;
        this.key = key;
        this.delta = delta;
        this.currentFrame = animations.get(key).getFrame(delta);
    }

    @Override
    public TextureRegion texture() {
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
        updateCurrentFrame();
    }

    @Override
    public void setActivity(String activity) {
        key.setActivity(activity);
        updateCurrentFrame();
    }

    @Override
    public void update(double delta) {
        this.delta += delta;
        if(!currentFrame.equals(animations.get(key).getFrame(delta))){
            currentFrame = animations.get(key).getFrame(delta);
        }
    }

    private void updateCurrentFrame(){
        this.delta = 0;
        this.currentFrame = animations.get(key).getFrame(delta);
    }
}
