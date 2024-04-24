package com.mygdx.game.V2.TextureSelector;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.V2.Util.ActivityType;
import com.mygdx.game.V2.Util.Direction;

import java.util.Objects;

/**
 * Represents an object that can select the appropriate {@link TextureRegion} at any time in the game.
 * Uses the {@link ActivityType} and the {@link Direction} to select the correct {@link Animation}, and uses the
 * {@link AnimationClock} to select the correct frame.
 */
public final class TextureSelector implements ITextureSelector{

    /**
     * The key.
     */
    private Key key;
    /**
     * The animation repository.
     */
    private final AnimationRepository repo;
    /**
     * The animation clock.
     */
    private final AnimationClock clock;

    /**
     * Creates a new {@link TextureSelector}.
     * The {@link TextureSelector} is mostly immutable, save for the {@link Key}, and the {@link TextureRegion}s stored
     * in the {@link Animation}s.
     * @param key The {@link Key}. Cannot be null.
     * @param repo The {@link AnimationRepository}. Cannot be null.
     * @param clock The {@link AnimationClock}. Cannot be null.
     */
    public TextureSelector(Key key, AnimationRepository repo, AnimationClock clock){
        Objects.requireNonNull(key, "Key is null.");
        Objects.requireNonNull(repo, "Repository is null.");
        Objects.requireNonNull(clock, "Clock is null.");
        this.key = key;
        this.repo = repo;
        this.clock = clock.copy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActivityType(ActivityType activityType){
        updateKey(activityType, key.direction());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(Direction direction){
        updateKey(key.activityType(), direction);
    }

    /**
     * Updates the {@link Key} with the given objects, if any of them are different from the current {@link Key}.
     * @param activityType The {@link ActivityType}. Cannot be null.
     * @param direction The {@link Direction}. Cannot be null.
     */
    private void updateKey(ActivityType activityType, Direction direction){
        if(activityType == key.activityType() && direction == key.direction())
            return;
        key = new Key(activityType, direction);
        clock.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TextureRegion getTexture(){
        return repo.get(key).getFrame(clock.delta());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseClock(long increaseInMillis){
        clock.increase(increaseInMillis);
    }

    /**
     * Compares this {@link TextureSelector} to the given object and returns true if they're equal. Returns false otherwise.
     * Two {@link TextureSelector}s are equal if they hold equal keys, repositories and clocks.
     *
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(!(other instanceof TextureSelector))
            return false;
        TextureSelector selector = (TextureSelector) other;
        return key.equals(selector.key) && repo.equals(selector.repo) && clock.equals(selector.clock);
    }

    /**
     * @return The hash code of this {@link TextureSelector}.
     */
    @Override
    public int hashCode(){
        int result = key.hashCode();
        result = result * 31 + repo.hashCode();
        result = result * 31 + clock.hashCode();
        return result;
    }

    /**
     * @return The string representation of this {@link TextureSelector}.
     */
    @Override
    public String toString(){
        return String.format("TextureSelector[key=%s, repository=%s, clock=%s]", key, repo, clock);
    }

    /**
     * @return A copy of this {@link TextureSelector}.
     */
    public TextureSelector copy(){
        return new TextureSelector(key, repo, clock.copy());
    }
}
