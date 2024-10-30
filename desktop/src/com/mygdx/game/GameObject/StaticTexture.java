package com.mygdx.game.GameObject;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameObject.Renderer;
import com.mygdx.game.Util.Vec2;
import com.mygdx.game.Util.Direction;

public record StaticTexture(TextureRegion texture) implements Renderer {

    @Override
    public Vec2 getOffSet() {
        return new Vec2(0, 0);
    }

    @Override
    public int getWidth() {
        return texture.getRegionWidth();
    }

    @Override
    public int getHeight() {
        return texture.getRegionHeight();
    }

    @Override
    public void setDirection(Direction direction) {
    }

    @Override
    public void setActivity(String activity) {
    }

    @Override
    public void update(double delta) {
    }
}
