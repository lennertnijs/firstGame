package com.mygdx.game.V2.TextureSelector;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.V2.ActivityType;
import com.mygdx.game.V2.Direction;

import java.util.Objects;

public final class TextureSelector implements ITextureSelector{

    private ActivityType activityType;
    private Direction direction;
    private final IAnimationRepository<Texture> animationRepo;
    private LoopAnimation<Texture> activeAnimation;
    private final IDeltaTime deltaTime;

    public TextureSelector(ActivityType activityType, Direction direction, IDeltaTime deltaTime, IAnimationRepository<Texture> repo){
        this.activityType = activityType;
        this.direction = direction;
        this.deltaTime = deltaTime;
        this.animationRepo = repo;
        updateActiveAnimation();
    }

    public void setActivityType(ActivityType activityType){
        Objects.requireNonNull(activityType, "Cannot set the ActivityType to null.");
        if(this.activityType == activityType){
            return;
        }
        this.activityType = activityType;
        updateActiveAnimation();
    }

    public void setDirection(Direction direction){
        Objects.requireNonNull(direction, "Cannot set the Direction to null.");
        if(this.direction == direction){
            return;
        }
        this.direction = direction;
        updateActiveAnimation();
    }

    private void updateActiveAnimation(){
        Animation<Texture> animation = animationRepo.getAnimation(activityType, direction);
        this.activeAnimation = new InfiniteLoopAnimation<>(animation);
    }

    public Texture getTexture(){
        return activeAnimation.getFrame(deltaTime.getDelta());
    }
}
