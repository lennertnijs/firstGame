package com.mygdx.game.Renderer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Dimensions;
import com.mygdx.game.Util.Direction;
import com.mygdx.game.Util.Vector;

public final class StaticTexture implements Renderer{

    private final TextureRegion texture;

    public StaticTexture(TextureRegion texture){
        this.texture = texture;
    }
    @Override
    public TextureRegion getTexture() {
        return texture;
    }

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
        throw new IllegalStateException();
    }

    @Override
    public void setActivity(String activity) {
        throw new IllegalStateException();
    }

    @Override
    public void update(double delta) {
        throw new IllegalStateException("Updating a delta of a static texture is non sensical.");
    }
}
