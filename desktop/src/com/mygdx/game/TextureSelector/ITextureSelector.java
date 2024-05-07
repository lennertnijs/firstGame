package com.mygdx.game.TextureSelector;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.ActivityType;
import com.mygdx.game.Util.Direction;

import java.util.NoSuchElementException;

/**
 * Represents an object that can select the appropriate {@link TextureRegion} at any time in the game.
 * Uses the {@link ActivityType} and the {@link Direction} to select the correct {@link Animation}, and uses the
 * {@link AnimationClock} to select the correct frame.
 */
public interface ITextureSelector {

    /**
     * Sets the {@link ActivityType} to the given {@link ActivityType}.
     * @param activityType The {@link ActivityType}. Cannot be null.
     *
     * @throws NullPointerException If the given {@link ActivityType} is null.
     */
    void setActivityType(ActivityType activityType);

    /**
     * Sets the {@link Direction} to the given {@link Direction}.
     * @param direction The {@link Direction}. Cannot be null.
     *
     * @throws NullPointerException If the given {@link Direction} is null.
     */
    void setDirection(Direction direction);

    /**
     * Fetches and returns the appropriate {@link TextureRegion}.
     *
     * @return The {@link TextureRegion}.
     * @throws NoSuchElementException If no {@link Animation} was found for the given {@link Key}.
     */
    TextureRegion getTexture();

    /**
     * Increases the clock with the given number (in millis).
     * @param increaseInMillis The increase (in millis). Cannot be negative.
     *
     * @throws IllegalArgumentException If the given increase is negative.
     */
    void increaseClock(long increaseInMillis);
}
