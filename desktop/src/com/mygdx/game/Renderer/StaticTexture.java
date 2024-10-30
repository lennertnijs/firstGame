package com.mygdx.game.Renderer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Vector;

public record StaticTexture(TextureRegion texture) implements Renderer {

    @Override
    public Vector getOffSet() {
        return null;
    }

    @Override
    public Dimensions getDimensions() {
        return new Dimensions(texture.getRegionHeight(), texture.getRegionWidth());
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
