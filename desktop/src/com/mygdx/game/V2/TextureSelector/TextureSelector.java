package com.mygdx.game.V2.TextureSelector;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.V2.Util.ActivityType;
import com.mygdx.game.V2.Util.Direction;

public final class TextureSelector implements ITextureSelector{

    private AnimationKey key;
    private final AnimationRepository repository;
    private final AnimationClock clock;

    public TextureSelector(ActivityType activityType, Direction direction, AnimationRepository repository, AnimationClock clock){
        this.key = new AnimationKey(activityType, direction);
        this.repository = repository;
        this.clock = clock;
    }

    public void setActivityType(ActivityType activityType){
        key = new AnimationKey(activityType, key.direction());
        clock.reset();
    }

    public void setDirection(Direction direction){
        key = new AnimationKey(key.activityType(), direction);
        clock.reset();
    }

    public void increaseClock(long l){
        clock.increase(l);
    }

    public TextureRegion getTexture(){
        return repository.getAnimation(key).getFrame(clock.delta());
    }
}
