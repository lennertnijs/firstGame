package game.game_object.renderer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.util.Vec2;

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
     * Fetches and returns the direction.
     */
    Direction getDirection();

    /**
     * Sets the direction in the Renderer.
     */
    void setDirection(Direction direction);

    /**
     * Fetches and returns the activity.
     */
    String getActivity();

    /**
     * Sets the activity in the Renderer.
     */
    void setActivity(String activity);

    /**
     * Updates the Renderer with the given delta (in millis).
     */
    void update(double delta);
}
