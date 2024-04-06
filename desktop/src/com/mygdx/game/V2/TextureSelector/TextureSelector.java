package com.mygdx.game.V2.TextureSelector;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.V2.ActivityType;
import com.mygdx.game.V2.Direction;

import java.util.Objects;

public final class TextureSelector {

    private final IDeltaTime deltaTime;
    private final IAnimationRepository<Texture> animationRepo;
    private ILoopedAnimation<Texture> activeAnimation;

    public TextureSelector(IDeltaTime deltaTime, IAnimationRepository<Texture> repo, ILoopedAnimation<Texture> activeAnimation){
        this.deltaTime = deltaTime;
        this.animationRepo = repo;
        this.activeAnimation = activeAnimation;
    }

    public Texture getTexture(){
        return activeAnimation.getFrame(deltaTime.getDelta());
    }

    public void changeAnimation(ActivityType activityType, Direction direction){
        validate(activityType, direction);
        Animation<Texture> animation = animationRepo.getAnimation(activityType, direction);
        this.activeAnimation = new InfiniteLoopedAnimation<>(animation);
    }

    public void changeAnimation(ActivityType activityType, Direction direction, int maxLoops){
        validate(activityType, direction);
        Animation<Texture> animation = animationRepo.getAnimation(activityType, direction);
        Animation<Texture> idleAnimation = animationRepo.getAnimation(ActivityType.IDLING, direction);
        this.activeAnimation = new FiniteLoopAnimation<>(animation, maxLoops, idleAnimation);
    }

    private void validate(ActivityType activityType, Direction direction){
        Objects.requireNonNull(activityType, "Cannot change the active IAnimation because the type is null.");
        Objects.requireNonNull(direction, "Cannot change the active IAnimation because the direction is null.");
    }
}
