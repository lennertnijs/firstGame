package com.mygdx.game.TextureSelector;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Util.Point;
import com.mygdx.game.WeekSchedule.ActivityType;
import com.mygdx.game.Util.Direction;

import java.util.Objects;

public final class TextureSelector implements ITextureSelector{

    private Key key;
    private final AnimationRepository repo;
    private final AnimationClock clock;

    public TextureSelector(Key key, AnimationRepository repo, AnimationClock clock){
        Objects.requireNonNull(key, "Key is null.");
        Objects.requireNonNull(repo, "Repository is null.");
        Objects.requireNonNull(clock, "Clock is null.");
        this.key = key;
        this.repo = repo;
        this.clock = clock.copy();
    }

    @Override
    public void setActivityType(ActivityType activityType){
        updateKey(activityType, key.direction());
    }

    @Override
    public void setDirection(Direction direction){
        updateKey(key.activityType(), direction);
    }

    private void updateKey(ActivityType activityType, Direction direction){
        if(activityType == key.activityType() && direction == key.direction())
            return;
        key = new Key(activityType, direction);
        clock.reset();
    }

    @Override
    public Frame getFrame(){
        return repo.get(key).getFrame(clock.getDeltaInMillis());
    }

    @Override
    public void increaseClock(long increaseInMillis){
        clock.increase(increaseInMillis);
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof TextureSelector))
            return false;
        TextureSelector selector = (TextureSelector) other;
        return key.equals(selector.key) && repo.equals(selector.repo) && clock.equals(selector.clock);
    }

    @Override
    public int hashCode(){
        int result = key.hashCode();
        result = result * 31 + repo.hashCode();
        result = result * 31 + clock.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("TextureSelector[key=%s, repository=%s, clock=%s]", key, repo, clock);
    }

    public TextureSelector copy(){
        return new TextureSelector(key, repo, clock.copy());
    }
}
