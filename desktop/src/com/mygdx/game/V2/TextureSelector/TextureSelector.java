package com.mygdx.game.V2.TextureSelector;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.V2.ActivityType;
import com.mygdx.game.V2.Direction;

import java.util.Objects;

public final class TextureSelector {

    private final DeltaTime deltaTime;
    private final AnimationRepo<Texture> animationRepo;
    private IAnimation<Texture> activeAnimation;

    public TextureSelector(DeltaTime deltaTime, AnimationRepo<Texture> repo, IAnimation<Texture> activeAnimation){
        this.deltaTime = deltaTime;
        this.animationRepo = repo;
    }

    public DeltaTime getDeltaTime(){
        return deltaTime;
    }

    public AnimationRepo<Texture> getAnimationRepo(){
        return animationRepo;
    }

    public IAnimation<Texture> getActiveAnimation(){
        return activeAnimation;
    }


    public void changeAnimation(ActivityType activityType, Direction direction){
        Objects.requireNonNull(activityType, "Cannot change the active IAnimation because the type is null.");
        Objects.requireNonNull(direction, "Cannot change the active IAnimation because the direction is null.");
        Animation<Texture> animation = animationRepo.getAnimation(activityType, direction);
        this.activeAnimation = new InfiniteLoopedAnimation<>(animation);
    }

    public void changeAnimation(ActivityType activityType, Direction direction, int maxLoops){
        Objects.requireNonNull(activityType, "Cannot change the active IAnimation because the type is null.");
        Objects.requireNonNull(direction, "Cannot change the active IAnimation because the direction is null.");
        Animation<Texture> animation = animationRepo.getAnimation(activityType, direction);
        Animation<Texture> idleAnimation = animationRepo.getAnimation(ActivityType.IDLING, direction);
        this.activeAnimation = new FiniteLoopAnimation<>(animation, maxLoops, idleAnimation);
    }

    public Texture getTexture(){
        return activeAnimation.get(deltaTime.getDelta());
    }
}
