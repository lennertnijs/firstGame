package com.mygdx.game.renderer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.UpdatedUtil.Vec2;
import com.mygdx.game.Util.Direction;

import java.util.Objects;

public final class StaticRenderer implements Renderer {

    private final Frame frame;

    public StaticRenderer(Frame frame){
        this.frame = Objects.requireNonNull(frame);
    }

    @Override
    public TextureRegion getTexture(){
        return frame.texture();
    }

    @Override
    public Vec2 getOffSet() {
        return frame.offset();
    }

    @Override
    public int getWidth() {
        return frame.scaledWidth();
    }

    @Override
    public int getHeight() {
        return frame.scaledHeight();
    }

    @Override
    public Direction getDirection() {
        throw new IllegalStateException("Cannot fetch the direction of a static texture.");
    }

    @Override
    public void setDirection(Direction direction) {
    }

    @Override
    public String getActivity() {
        throw new IllegalStateException("Cannot fetch the activity of a static texture.");
    }

    @Override
    public void setActivity(String activity) {
    }

    @Override
    public void update(double delta) {
    }
}
