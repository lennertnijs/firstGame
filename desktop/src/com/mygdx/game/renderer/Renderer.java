package com.mygdx.game.renderer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.UpdatedUtil.Vec2;
import com.mygdx.game.Util.Direction;

public interface Renderer {

    /**
     * Fetches and returns the active Texture.
     */
    TextureRegion getTexture();

    /**
     * Fetches and returns the offset of the active Texture (to be added to its current position).
     */
    Vec2 getOffSet();

    /**
     * Fetches and returns the scaled width of the active Texture.
     */
    int getWidth();
    /**
     * Fetches and returns the scaled height of the active Texture.
     */
    int getHeight();

    /**
     * Sets the direction in the Renderer.
     */
    void setDirection(Direction direction);

    /**
     * Sets the activity in the Renderer.
     */
    void setActivity(String activity);

    /**
     * Updates the Renderer with the given delta (in millis).
     */
    void update(double delta);
}
